package com.learning.view;

import com.learning.model.EmailAccount;
import com.learning.model.EmailTreeItem;
import javafx.scene.control.TreeItem;

public class EmailManager {
    private EmailTreeItem<String> foldersRoot = new EmailTreeItem<>();

    public TreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount){
        TreeItem<String> emailAccountTree = new TreeItem<>(emailAccount.getAddress());
        emailAccountTree.setExpanded(true);
        emailAccountTree.getChildren().add(new TreeItem<String>("INBOX"));

        foldersRoot.getChildren().add(emailAccountTree);

    }
}
