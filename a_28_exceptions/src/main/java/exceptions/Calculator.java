package exceptions;

public class Calculator {
	public int calculate(char op, int number1, int number2) {
		// Berechnung je nach Operator
		switch (op) {
		case '+':
			return number1 + number2;
		case '-':
			return number1 - number2;
		case '/':
			// handle division by zero
			try {
				return number1 / number2;
			} catch (ArithmeticException e){
				//  ArithmeticException in eine CalculationException einpacken
				throw new CalculationException("Fehler in der Division", e);
			}
		case '*':
			return number1 * number2;
		default:
			// throw exception
			throw new CalculationException("Ungültiger Operator " + op);
			//return 0;
		}

	}
}
