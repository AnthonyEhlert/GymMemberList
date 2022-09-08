package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.GymMember;

/**
 * @author Tony Ehlert - aehlert
 * CIS175 - Fall 2022
 * Sep 7, 2022
 */
public class GymMemberHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("GymMemberList");
	
	// method to add/insert a new gym member to the database
	public void insertMember(GymMember gm) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(gm);
		em.getTransaction().commit();
		em.close();
	}
	
	//method to show display all members in database
	public List<GymMember> showAllMembers() {
		EntityManager em = emfactory.createEntityManager();
		List<GymMember> allMembers = em.createQuery("SELECT i FROM GymMember i").getResultList();
		return allMembers;
	}
	
	// method to delete member from database
	public void deleteMember(GymMember toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GymMember> typedQuery = em.createQuery("select gm from GymMember gm where gm.firstName = :selectedFirstName and gm.lastName = :selectedLastName", GymMember.class);
		
		//Substitute parameter with actual data from the toDelete member
		typedQuery.setParameter("selectedFirstName", toDelete.getFirstName());
		typedQuery.setParameter("selectedLastName", toDelete.getLastName());
		
		//ensures only one result is selected for deletion
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list member
		GymMember result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param toEdit- GymMember object to be edited
	 */
	public void updateMember(GymMember toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param idToEdit- GymMember id parameter to search database for
	 * @return- gymMember object found using id
	 */
	public GymMember searchForMemberById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction();
		GymMember found = em.find(GymMember.class, idToEdit);
		return found;
	}

	/**
	 * @param firstName- GymMember firstName parameter to search database for
	 * @return- list of GymMember objects that have matching firstName parameter
	 */
	public List<GymMember> searchForMemberByFirstName(String firstName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction();
		TypedQuery<GymMember> typedQuery = em.createQuery("select gm from GymMember gm where gm.firstName = :selectedFirstName", GymMember.class);
		typedQuery.setParameter("selectedFirstName", firstName);
		List<GymMember> foundMembers = typedQuery.getResultList();
		em.close();
		return foundMembers;
	}

	/**
	 * @param lastName- GymMember lastName parameter to search database for
	 * @return- list of GymMember objects that have matching lastName parameter
	 */
	public List<GymMember> searchForMemberByLastName(String lastName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction();
		TypedQuery<GymMember> typedQuery = em.createQuery("select gm from GymMember gm where gm.lastName = :selectedLastName", GymMember.class);
		typedQuery.setParameter("selectedLastName", lastName);
		List<GymMember> foundMembers = typedQuery.getResultList();
		em.close();
		return foundMembers;
	}
	
	// method to ensure database has no entry points when finished
	public void cleanUp() {
		emfactory.close();
	}
	
	
	
}
