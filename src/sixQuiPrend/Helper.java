package sixQuiPrend;

public class Helper {

	/**
	 * V�rifie si une cha�ne de caract�re est num�rique ou non.
	 * 
	 * @param strNum la cha�ne � v�rifier
	 * @return vrai si num�rique, faux si non
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
