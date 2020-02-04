package com.core.blore.bo;

import java.util.ArrayList;
import java.util.List;

import com.blore.bean.CoreRequest;
import com.blore.bean.CoreResponse;
import com.core.blore.dal.UserDAO;

/**
 * @athor vijay pawar
 */

public class ContactsHelper extends BaseBO {

	public CoreResponse getBloreContacts(CoreRequest coreRequest) {
		CoreResponse coreResponse = new CoreResponse();
		List<String> matchedCons = new ArrayList<String>();
		String contactToReturn = "";
		List<String> contacts = (List<String>) coreRequest.getValue("contacts");
		try {
			if (contacts != null) {
				List<String> bloreCotacts = new UserDAO(getEntityManager())
						.getMatchedBloreContacts(contacts);
				System.out.println("client contacts are ::" + contacts);
				System.out.println("blore cotact are :: " + bloreCotacts);
				if (bloreCotacts != null) {
					for (String clientCon : contacts) {
						contactToReturn = clientCon;
						clientCon = clientCon.replace("+", "");
						clientCon = clientCon.replace(" ", "");

						for (String bloreCon : bloreCotacts) {
							if (bloreCon.contains(clientCon)) {
								if (!matchedCons.contains(contactToReturn))
									matchedCons.add(contactToReturn);
							}

						}

					}
				}
				coreResponse.setData(matchedCons);
				coreResponse.setMsgToClient(1200);
			} else {
				System.out
						.println("Contact list is empty *********************** from client");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}

		return coreResponse;
	}

	public void contactTest() {
		CoreRequest coreRequest = new CoreRequest();
		UserDAO dao = new UserDAO(getEntityManager());
		List<String> contactsTomatch = new ArrayList<String>();
		contactsTomatch.add("9420280997");
		System.out.println(dao.getMatchedBloreContacts(contactsTomatch));
	}

	public static void main(String[] args) {
		new ContactsHelper().contactTest();
	}

}
