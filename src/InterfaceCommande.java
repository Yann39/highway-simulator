/*
 * InterfaceCommande.java
 *
 * Created on 18 janvier 2007, 16:20
 *
 * @author Yann <rockyracer@mailfence.com>
 */

import java.util.EventListener;

public interface InterfaceCommande extends EventListener {
    void NbVoituresChange(double nouveauNbVoitures);

    void VitesseMoyenneChange(double nouvelleVitesseMoyenne);

    void VitessePlusLentChange(double nouvelleVitessePlusLent);

    void VitessePlusRapideChange(double nouvelleVitessePlusRapide);
}