package sixQuiPrend;

public class Helper {

	/**
	 * Vérifie si une chaîne de caractère est numérique ou non.
	 * 
	 * @param strNum la chaîne à vérifier
	 * @return vrai si numérique, faux si non
	 */
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
