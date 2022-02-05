package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Student;

/**
 * @author DeoTheHung -The Deo
 * CIS175 Spring 2022
 * Feb 3, 2022
 */
public class StudentHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Student");
	
	public void insertStudent(Student st) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(st);
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteStudent(Student toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Student> typedQuery = em.createQuery("select st from Student st where st.lastName = :selectedLastName and st.firstName = :selectedFirstName",Student.class);
		
		typedQuery.setParameter("selectedLastName", toDelete.getLastName());
		typedQuery.setParameter("selectedFirstName", toDelete.getFirstName());
		
		typedQuery.setMaxResults(1);
		
		Student result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Student> searchForStudentByFirstName(String firstName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Student> typedQuery = em.createQuery("select st from Student st where st.firstName = :selectedFirstName",Student.class);
		typedQuery.setParameter("selectedFirstName", firstName);
		
		List<Student> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		
	}
	
	public List<Student> searchForStudentByLastName(String lastName){
		EntityManager em  = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Student> typedQuery = em.createQuery("select st from Student st where st.lastName = :selectedLastName", Student.class);
		typedQuery.setParameter("seletedLastName", lastName);
		
		List<Student> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;		
	}
	
	public Student searchForStudentByStudentId(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Student found = em.find(Student.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateStudent(Student toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Student> showAllStudent(){
		EntityManager em = emfactory.createEntityManager();
		List<Student> allStudent = em.createQuery("select i from Student i", Student.class).getResultList();
		return allStudent;
	}
	
	public void cleanUp() {
		emfactory.close();
	}

}
