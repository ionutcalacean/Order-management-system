package bll.validators;

public interface Validator<T>{
   /**
    * se valideaza un anumit atribut, ca acesta sa respecta o anumita logica, o lungime maxima, sau o limita
    * @param t Clasa din care face parte atributul
    */
	public void validate(T t);
}
