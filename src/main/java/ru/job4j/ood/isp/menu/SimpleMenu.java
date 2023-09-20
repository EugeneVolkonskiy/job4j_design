package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        Optional<ItemInfo> item = findItem(parentName);
        if (Objects.equals(Menu.ROOT, parentName)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            rsl = true;
        } else if (item.isPresent()) {
            item.get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> item = findItem(itemName);
        MenuItemInfo menuItemInfo = null;
        if (item.isPresent()) {
            MenuItem menuItem = item.get().menuItem;
            List<String> childrenList = menuItem.getChildren().stream().map(MenuItem::getName).collect(Collectors.toList());
            menuItemInfo = new MenuItemInfo(itemName, childrenList, menuItem.getActionDelegate(), item.get().number);
        }
        return Optional.ofNullable(menuItemInfo);
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new MenuItemInfoIterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator dfsIterator = new DFSIterator();
        ItemInfo result = null;
        if (name != null) {
            while (dfsIterator.hasNext()) {
                ItemInfo next = dfsIterator.next();
                if (name.equals(next.menuItem.getName())) {
                    result = next;
                    break;
                }
            }
        }
        return Optional.ofNullable(result);
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class MenuItemInfoIterator implements Iterator<MenuItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        MenuItemInfoIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public MenuItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            String space = "    ";
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(space.concat(lastNumber.concat(String.valueOf(currentNumber--)).concat(".")));
            }
            return new MenuItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
