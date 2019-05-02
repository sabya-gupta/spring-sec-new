package my.spring.entity;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAO {

	@Resource(name="&mysecondbean")
	private LocalContainerEntityManagerFactoryBean mysecondbean;
//	@Autowired
//	private Map<String, LocalContainerEntityManagerFactoryBean> entityManagerFactoryMap;


	@Transactional("transactionManager")
	public List<Customer> getCustomers() {
		//System.out.println(entityManagerFactoryMap);
		EntityManager em = mysecondbean.getNativeEntityManagerFactory().createEntityManager();
		//EntityManager em = entityManagerFactoryMap.get("&mysecondbean").getNativeEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select c from Customer c");
		List<Customer> list = query.getResultList();

		for (Customer e : list) {
			System.out.println("Customer :" + e);
		}
		return list;
	}

}
