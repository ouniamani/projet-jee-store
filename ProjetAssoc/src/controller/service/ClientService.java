package controller.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Client;

public class ClientService {

	private EntityManager em;

	public ClientService(EntityManager pem) {
		em = pem;
	}

	/**
	 * Methode qui controle un couple user/password
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean checkUserPassword(String user, String password){
		//Controle de l'utilisateur
		Client my_cli = em.find(Client.class, user);

		return (my_cli != null && my_cli.getMdp().equals(password) ? true:false);

	}

	public boolean userExist(String user){
		//Controle de l'utilisateur
		Client my_cli = em.find(Client.class, user);

		return (my_cli != null ? true:false);
	}

	/**
	 * M�thode qui cree un utilisateur en base
	 * @param id
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param cp
	 * @param ville
	 * @param pays
	 * @return
	 */
	public boolean create(String id, String mdp, String nom, String prenom, String adresse, String cp, String ville, String pays){
		//EntityManager em = emf.createEntityManager();
		try{
			EntityTransaction entr=em.getTransaction();
			entr.begin();
			Client newClient = new Client();
			newClient.setIdentifiant(id);
			newClient.setMdp(mdp);
			newClient.setNom(nom);
			newClient.setPrenom(prenom);
			newClient.setAdresse(adresse);
			newClient.setCp(cp);
			newClient.setVille(ville);
			newClient.setPays(pays);
			em.persist(newClient);
			entr.commit();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
