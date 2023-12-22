package model;
import jakarta.persistence.*;

@Entity
@Table(name = "latteview")
public class LatteViewModel {
    @Id
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column
    private int calories;

    @Column(name = "total_fat")
    private double totalFat;

    @Column(name = "saturated_fat")
    private double saturatedFat;

    @Column
    private double carbs;

    @Column
    private double protein;

    @Column
    private double sugars;

    // Gettery i settery dla id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Gettery i settery dla itemName
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Gettery i settery dla calories
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    // Gettery i settery dla totalFat
    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    // Gettery i settery dla saturatedFat
    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    // Gettery i settery dla carbs
    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    // Gettery i settery dla protein
    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    // Gettery i settery dla sugars
    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }
}
