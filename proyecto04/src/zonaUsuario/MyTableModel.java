package zonaUsuario;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private int[][] carton;
	
	public MyTableModel(int[][] carton) {
		this.carton = carton;
	}

	
	// Para saber el total de filas que tiene que mostrar 
	//en la tabla
	@Override
	public int getRowCount() {
		// es el primer numero del []
		int filas = carton.length;
		return filas;
	}

	
	// Para saber el número de columnas
    // a representar
	@Override
	public int getColumnCount() {
		int columnas = carton[0].length;
		return columnas;
	}
	
	public int[][] getCarton(int[][] carton){
		return carton;
	}
	
	// Para obtener el valor
    // de una celda (row, column) concreta
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
				
		return getCarton(carton)[rowIndex][columnIndex];
	}
	
	
	

}

