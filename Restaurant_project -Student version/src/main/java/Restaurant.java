import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<String> itemName = new ArrayList<String>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        LocalTime dtcurrentTime = getCurrentTime();
        System.out.println("current time: "+dtcurrentTime);
        boolean isOpen = dtcurrentTime.isAfter(openingTime);
        System.out.println(isOpen);
        boolean isClosed = dtcurrentTime.isBefore(closingTime);
        System.out.println(isClosed);
        if(isOpen&&isClosed){
            return true;
        }else{
            return false;
        }
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    public int getMenuSize(){return getMenu().size();}

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public List<String> getItems() {
        return itemName;
    }

    public void selectItemForOrder(String item) {
        itemName.add(item);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }
//method to calculate order value from selected menu items.
    public int calculateTotalOrderCost(List<String> itemNames){
        int orderTotal = 0;
        for(String name: itemNames){
            for(Item item: menu) {
                if(item.getName().equals(name))
                    orderTotal = orderTotal+item.getPrice();
            }
        }
        return orderTotal;
    }

}
