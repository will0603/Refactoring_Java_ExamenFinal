import java.util.HashMap;

public class RentalInfo {
	/*
	 * Alumno: Huallpartupa Gallegos Wilfredo
	 * Cambio: 
	 * Se agregó la constante "code", para eliminar la duplicidad del código, ya 
	 * que esta palabra "regular" se repite 3 veces, con este cambio solucionamos el 
	 * code smell de duplicidad de código
	 */
	String code = "regular";
	
  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", code));
    movies.put("F002", new Movie("Matrix", code));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      thisAmount = AmountMovie(thisAmount, movies, r);

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movies.get(r.getMovieId()).getCode() == "new" && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movies.get(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
  
  /*
   * Alumno: Huallpartupa Gallegos Wilfredo
   * Cambio: 
   * Se agregó una nueva función para reducir la complejida del código, con ello 
   * buscamos que el código sea mas legible y mas fácil de comprender.
   */
  
  public double AmountMovie(double thisAmount, HashMap<String, Movie> movies, MovieRental r) {
	  if (movies.get(r.getMovieId()).getCode().equals(code)) {
	        thisAmount = 2;
	        if (r.getDays() > 2) {
	          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
	        }
	      }
	      if (movies.get(r.getMovieId()).getCode().equals("new")) {
	        thisAmount = r.getDays() * 3;
	      }
	      if (movies.get(r.getMovieId()).getCode().equals("childrens")) {
	        thisAmount = 1.5;
	        if (r.getDays() > 3) {
	         thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
	        }
	      }
	return thisAmount;
  }
}
