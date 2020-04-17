import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigInteger;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();

        try {
            Book b2 = new Book("Nazwisko2", "Imie2", new BigInteger("1234567890123"), 1998, 12.20);
            Book b3 = new Book("Nazwisko3", "Imie3", new BigInteger("1234567890145"), 2018, 15.20);
            Book b4 = new Book("Nazwisko4", "Imie4", new BigInteger("1234567890146"), 2005, 19.99);
            em.getTransaction().begin();
            em.persist(b2);
            em.persist(b3);
            em.persist(b4);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }
    }
}
