package ch.hsr.waktu.presentation.model;

import java.util.List;
import java.util.Vector;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import ch.hsr.waktu.domain.Domain;
import ch.hsr.waktu.domain.User;
import ch.hsr.waktu.domain.UserProperties;

public class UserTreeModel implements TreeModel{
	private List<User> users;
	private List<UserProperties> properties;
	
	private Vector<TreeModelListener> treeModelListeners =
        new Vector<TreeModelListener>();
	
	public UserTreeModel() {
		users = Domain.getInstance().getUsers();
		properties = Domain.getInstance().getUserProperties();
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.addElement(l);
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (parent.equals("Users")) {
			return users.get(index);
		} else if (parent instanceof User) {
			return properties.get(index);
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent.equals("Users")) {
			return users.size();
		} else if (parent instanceof User) {
			return properties.size();
		}
		return 0;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent.equals("Users")) {
			return users.indexOf(child);
		} else if (parent instanceof User) {
			return properties.indexOf(child);
		}
		return 0;
	}

	@Override
	public Object getRoot() {
		return "Users";
	}

	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof UserProperties) {
			return true;
		}
		return false;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.removeElement(l);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		System.out.println("*** valueForPathChanged : "
                + path + " --> " + newValue);
	}

}
