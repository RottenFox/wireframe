package com.example.Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.Formula;

/**
 *
 * @author HP
 */

@Entity
@Table(name = "item")
public class AddItem {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    
    private Integer storeId;
    
    @Version
private Long version;
    
    @OneToMany(mappedBy = "item")
    private List<Purchase> purchases;
    
    @Column(name = "Item_name",unique = true, nullable = false)
    private String productname;
    
 @Column(name = "Item_code")
    private String itemCode;
 
 @Column(name = "Item_size")
    private Float Item_size;
 
@Column(name = "Item_price")
    private Float Item_price;

@Column(name = "Base_Unit")
    private String Base_Unit;

@Column(name = "Derived_unit")
    private String Derived_unit;

@Column(name = "Purchase_price")
    private Float Purchase_price;

@Column(name = "Item_Category")
    private String Item_Category;
    
@Column(name = "Item_openstock")
    private Float Item_openstock;

@Column(name = "Item_stockAmount")
    private Float Item_stockAmount;

@Column(name = "wholesale_qty")
    private Float wholesale_qty;

@Column(name = "Wholesale_price")
    private Float Wholesale_price;

@Column(name = "minstock")
    private Float minstock;

@Column(name = "maxstock")
    private Float maxstock;

@Column(name = "location")
    private String location;

@Column(name = "Date")
    private LocalDate  date;

@Column(name = "purchase_qty")
private Float purchaseqty;

@Column(name = "sale_qty")
private Float saleqty;

@Column(name = "Average_cost")
private Float averageCost;



@Transient
private final SimpleFloatProperty Stock = new SimpleFloatProperty();
@Transient
private final SimpleFloatProperty Stockvalue = new SimpleFloatProperty();
@Transient
private Float Amount;
@Transient
private final FloatProperty qty = new SimpleFloatProperty();
@Transient
private int rownumber;
@Transient
private List<AddItem> children;

    public List<AddItem> getChildren() {
        return children;
    }

    public void setChildren(List<AddItem> children) {
        this.children = children;
    }

    


 // This annotation is used for large objects

   @ElementCollection
    @CollectionTable(name = "itempicture", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "picture", nullable = true) // Customize the column within the collection table
    @Lob
    private List<byte[]> pictures;

   
   {
        Stock.bind(Bindings.createFloatBinding(() ->
                        Optional.ofNullable(getPurchaseqty() + getItem_openstock() - (getSaleqty())).orElse(0.0f)));
        
//         Stockvalue.bind(Bindings.createFloatBinding(() ->
//                        Optional.ofNullable( getStock()  * getAverageCost()).orElse(0.0f)));
    }
   
   public StringProperty productnameProperty() {
        return new SimpleStringProperty(getProductname());
    }
   public StringProperty itemPriceProperty() {
        return new SimpleStringProperty(getItem_price().toString());
    }
   public StringProperty stockProperty() {
        return new SimpleStringProperty(getStock().toString());
    }
   public StringProperty purchasePriceProperty() {
        return new SimpleStringProperty(getPurchase_price().toString());
    }
   
   public StringProperty averageCostProperty() {
        return new SimpleStringProperty(getAverageCost().toString());
    }
  public StringProperty StockvalueProperty() {
        return new SimpleStringProperty(getstockVlue().toString());
    }

    public SimpleFloatProperty getStockvalue() {
        return Stockvalue;
    }
   
    public Float getAverageCost() {
        return Optional.ofNullable( averageCost).orElse(0f);
    }

    public void setAverageCost(Float averageCost) {
        this.averageCost = averageCost;
    }
   
    public void setPurchaseqty(Float purchaseqty) {
        this.purchaseqty = purchaseqty;
    }

    public void setSaleqty(Float saleqty) {
        this.saleqty = saleqty;
    }
    
    public SimpleFloatProperty getStockProperty() {
        return Stock;
    }
    public Float getstockVlue(){
        return Optional.ofNullable( getStock()  * getAverageCost()).orElse(0f);
    }
    public void setStock(Float Stock) {
        this.Stock.set(Stock);
    }
    
    

    public void setRownumber(int rownumber) {
        this.rownumber = rownumber+1;
    }

    public int getRownumber() {
        return rownumber;
    }

   public void setQty(Float qty) {
    this.qty.set(Optional.ofNullable(qty).orElse(1f));
}
    public Float getQty() {
        return Optional.ofNullable(qty.get()).orElse(0.0f);
    }

    public FloatProperty qtyProperty() {
        return qty;
    }
    public void setAmount(Float Amount) {
        this.Amount = Optional.ofNullable(Amount).orElse(qty.get() * Purchase_price);
    }

    public Float getAmount() {
        return Optional.ofNullable(Amount ).orElse(0f);
    }

    public Float getStock() {
        return Optional.ofNullable(getPurchaseqty() + getItem_openstock() - (getSaleqty()) ).orElse(0f);
      
    }

    public Float getPurchaseqty() {
        return Optional.ofNullable(purchaseqty ).orElse(0f);
    }

    public Float getSaleqty() {
        return Optional.ofNullable(saleqty ).orElse(0f);
    }
    public Integer getStoreId() {
        return storeId;
    }

    public String getProductname() {
        return Optional.ofNullable(productname).orElse("");
    }

    public String getItemCode() {
        
        return Optional.ofNullable(itemCode).orElse("");
    }

    public int getItem_size() {
       return Optional.ofNullable(Item_size).orElse(0f).intValue();
    }

    public Float getItem_price() {
      
        return Optional.ofNullable(Item_price).orElse(0f);
    }

    public String getBase_Unit() {
        return Base_Unit;
    }

    public String getDerived_unit() {
       
        return Optional.ofNullable(Derived_unit).orElse("");
    }

    public Float getPurchase_price() {
        return Optional.ofNullable(Purchase_price).orElse(0f);
    }

    public String getItem_Category() {
       
        return Optional.ofNullable(Item_Category).orElse("");
    }

    public Float getItem_openstock() {
      
        return Optional.ofNullable(Item_openstock).orElse(0f);
    }

    public Float getItem_stockAmount() {
        
        return Optional.ofNullable(Item_stockAmount).orElse(0f);
    }

    public Float getWholesale_qty() {
       
        return Optional.ofNullable(wholesale_qty).orElse(0f);
    }

    public Float getWholesale_price() {
       
        return Optional.ofNullable(Wholesale_price).orElse(0f);
    }

    public Float getMinstock() {
      
        return Optional.ofNullable(minstock).orElse(0f);
    }

    public Float getMaxstock() {
       
        return Optional.ofNullable(maxstock).orElse(0f);
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<byte[]> getPictures() {
        return pictures;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public void setProductname(String Productname) {
        this.productname = Productname;
    }

    public void setItemCode(String Item_code) {
        this.itemCode = Item_code;
    }

    public void setItem_size(Float Item_size) {
        this.Item_size = Item_size;
    }

    public void setItem_price(Float Item_price) {
        this.Item_price = Item_price;
    }

    public void setBase_Unit(String Base_Unit) {
        this.Base_Unit = Base_Unit;
    }

    public void setDerived_unit(String Derived_unit) {
        this.Derived_unit = Derived_unit;
    }

    public void setPurchase_price(Float Purchase_price) {
        this.Purchase_price = Purchase_price;
    }

    public void setItem_Category(String Item_Category) {
        this.Item_Category = Item_Category;
    }

    public void setItem_openstock(Float Item_openstock) {
        this.Item_openstock = Item_openstock;
    }

    public void setItem_stockAmount(Float Item_stockAmount) {
        this.Item_stockAmount = Item_stockAmount;
    }

    public void setWholesale_qty(Float wholesale_qty) {
        this.wholesale_qty = wholesale_qty;
    }

    public void setWholesale_price(Float Wholesale_price) {
        this.Wholesale_price = Wholesale_price;
    }

    public void setMinstock(Float minstock) {
        this.minstock = minstock;
    }

    public void setMaxstock(Float maxstock) {
        this.maxstock = maxstock;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDate Date) {
        this.date = Date;
    }

    public void setPictures(List<byte[]> pictures) {
        this.pictures = pictures;
    }
   
}