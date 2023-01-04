package visualizacionGenericos;

import java.util.Collection;

import javax.swing.DefaultListModel;


public class ModeloListaValoresCantados extends DefaultListModel<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    public ModeloListaValoresCantados(Collection<Integer> allNumbers) {
        add(allNumbers);
    }

    public void add(Collection<Integer> allNumbers) {
        for (Integer i : allNumbers) {
            addElement(i);
        }
    }

}
