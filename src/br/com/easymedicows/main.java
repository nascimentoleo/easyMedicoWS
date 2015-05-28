package br.com.easymedicows;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalizacaoMedicos loc = new LocalizacaoMedicos();
		loc.setUser("cubanas");
		double d = Double.parseDouble("-51.925280");
		loc.setLatitude("-51.925280");
		loc.setLongitude("-51.925280");
		loc.setAtivo("S");
		LocalizacaoMedicosDAO dao = new LocalizacaoMedicosDAO();
		dao.inserirLocalizacao(loc);
		
		
	}

}
