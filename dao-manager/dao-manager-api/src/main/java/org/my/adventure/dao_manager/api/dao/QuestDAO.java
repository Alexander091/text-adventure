package org.my.adventure.dao_manager.api.dao;

import org.my.adventure.dao_manager.api.entities.Quest;

import javax.ejb.Remote;
import java.rmi.RemoteException;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Remote
public interface QuestDAO {
    Quest getTestObject() throws RemoteException;
}
