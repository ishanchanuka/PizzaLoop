package lk.kln.ac.pizzaloop;

public class Product {


    private String name;
    private String description;
    private double price;
    private String image_url;

    public Product( String name, String description, double price, String image_url) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;


    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
