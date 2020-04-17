import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="bookCatalog")
@SessionScoped
public class BookCatalogBean {

    String addFirstName, addLastName, updateLastName, updateFirstName, addISBN, updateISBN;
    Integer addYear, updateYear, updateId;
    Double addPrice, updatePrice;
    boolean updateView=false;


    EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
    EntityManager em = factory.createEntityManager();


    public String getAddFirstName() {
        return addFirstName;
    }

    public void setAddFirstName(String addFirstName) {
        this.addFirstName = addFirstName;
    }

    public String getAddLastName() {
        return addLastName;
    }

    public void setAddLastName(String addLastName) {
        this.addLastName = addLastName;
    }

    public String getAddISBN() {
        return addISBN;
    }

    public void setAddISBN(String addISBN) {
        this.addISBN = addISBN;
    }

    public Double getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Double addPrice) {
        this.addPrice = addPrice;
    }

    public Integer getAddYear() {
        return addYear;
    }

    public void setAddYear(Integer addYear) {
        this.addYear = addYear;
    }

    public boolean isUpdateView() {
        return updateView;
    }

    public void setUpdateView(boolean updateView) {
        this.updateView = updateView;
    }

    public String getUpdateLastName() {
        return updateLastName;
    }

    public void setUpdateLastName(String updateLastName) {
        this.updateLastName = updateLastName;
    }

    public String getUpdateFirstName() {
        return updateFirstName;
    }

    public void setUpdateFirstName(String updateFirstName) {
        this.updateFirstName = updateFirstName;
    }

    public String getUpdateISBN() {
        return updateISBN;
    }

    public void setUpdateISBN(String updateISBN) {
        this.updateISBN = updateISBN;
    }

    public Integer getUpdateYear() {
        return updateYear;
    }

    public void setUpdateYear(Integer updateYear) {
        this.updateYear = updateYear;
    }

    public Double getUpdatePrice() {
        return updatePrice;
    }

    public void setUpdatePrice(Double updatePrice) {
        this.updatePrice = updatePrice;
    }

    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        try {
            Query q = em.createQuery("from Book", Book.class);
            books = q.getResultList();
            for (Book b : books)
                System.out.println(b);
        }
        catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
        return books;
    }

    public boolean canAddString(String string){
        if (string!=null){
            if (!string.isBlank()){
                return true;
            }
        }
        return false;
    }

    public void updateViewOpen(Book book){
        updateView=true;
        updateLastName=book.getLastName();
        updateFirstName=book.getFirstName();
        updateISBN=book.getNumberISBN().toString();
        updateYear=book.getYear();
        updatePrice=book.getPrice();
        updateId=book.getId();
    }

    public void add(){
        if(canAddString(addLastName) && canAddString(addFirstName) && canAddString(addISBN) && addPrice!=null && addYear!=null) {
            Book b = new Book(addLastName,addFirstName,new BigInteger(addISBN),addYear,addPrice);
            try {
                em.getTransaction().begin();
                em.persist(b);
                em.getTransaction().commit();
            } catch (Exception e) {
                System.err.println("Blad przy dodawaniu rekordu: " + e);
            }
            addLastName="";
            addFirstName="";
            addISBN=null;
            addYear=null;
            addPrice=null;
        }
    }

    public void remove(Book book){
        try {
            if (book!=null){
                em.getTransaction().begin();
                em.remove(book);
                em.getTransaction().commit();
            }
        }
        catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
    }

    public void update(){
        if(canAddString(updateLastName) && canAddString(updateFirstName) && canAddString(updateISBN) && updatePrice!=null && updateYear!=null) {
            try {
                em.getTransaction().begin();
                Book updateBook = em.find(Book.class,updateId);
                updateBook.setLastName(updateLastName);
                updateBook.setFirstName(updateFirstName);
                updateBook.setNumberISBN(new BigInteger(updateISBN));
                updateBook.setYear(updateYear);
                updateBook.setPrice(updatePrice);
                em.getTransaction().commit();
                updateView=false;
            } catch (Exception e) {
                System.err.println("Blad przy dodawaniu rekordu: " + e);
            }
        }
    }


}
