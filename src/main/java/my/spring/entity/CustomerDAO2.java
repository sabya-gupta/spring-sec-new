package my.spring.entity;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAO2 {


	@Resource(name="&entityManagerFactory")
	private LocalContainerEntityManagerFactoryBean entityManagerFactory2;
//	private Map<String, LocalContainerEntityManagerFactoryBean> entityManagerFactory2;


	@Transactional("transactionManager2")
	public List<Customer> getCustomers2() {
//		System.out.println(entityManagerFactoryMap);
		EntityManager em = entityManagerFactory2.getNativeEntityManagerFactory().createEntityManager();
//		EntityManager em = entityManagerFactoryMap.get().getNativeEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select c from Customer c");
		List<Customer> list = query.getResultList();

		for (Customer e : list) {
			System.out.println("Customer2 :" + e);
		}
		return list;
	}

}
