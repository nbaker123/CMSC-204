/**
 * The main purpose of this class is to provide a utility
 * that can convert infix expressions to postfix expressions
 * and vice-versa, as well as evaluate postfix expressions.
 * @author Nicholas A. Baker
 *
 */
public class Notation {

	/**
	 * Accepts an infix expression as a String and converts it to a postfix expression
	 * @param infix
	 * @return A String representation of the infix expression as a postfix expression
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix(String infix) 
			throws InvalidNotationFormatException{

		NotationStack<Character> stack = new NotationStack<Character>(infix.length());
		NotationQueue<Character> solution = new NotationQueue<Character>(infix.length());
		String finalSolution = "";

		try {
			for (int i = 0; i < infix.length(); i++) {
				if (infix.charAt(i) >= '0' && infix.charAt(i) <= '9')
					solution.enqueue(infix.charAt(i));

				if (infix.charAt(i) == '(')
					stack.push(infix.charAt(i));

				/*
				 * This massive block is for if
				 * the current character is an
				 * operator
				 */
				if (infix.charAt(i) == '+' || infix.charAt(i) == '-' || infix.charAt(i) == '*'
						|| infix.charAt(i) == '/') {

					if (!stack.isEmpty()) {

						/*
						 * While the character at the top of the stack
						 * is an operator with higher or equal precedence to
						 * the current character (which is an operator),
						 * said operators will be popped from the stack and
						 * added to the solution queue
						 */
						if(infix.charAt(i) == '+' || infix.charAt(i) == '-') {
							while(stack.top() != '(')
								solution.enqueue(stack.pop());
						}
						else {
							while(stack.top() == '*' || stack.top() == '/') 
								solution.enqueue(stack.pop());
						}
					}
					stack.push(infix.charAt(i));
				}

				if(infix.charAt(i) == ')') {

					/*
					 * puts all of the operators into
					 * the solution queue
					 */
					while(stack.top() != '(')
						solution.enqueue(stack.pop());

					//discards the left parenthesis
					stack.pop();
				}
			}

			/*
			 * Assuming that there are no left 
			 * parenthesis in the stack at this point
			 */
			while(!stack.isEmpty())
				solution.enqueue(stack.pop());

			//Puts each character from the queue into the final solution
			int g = solution.size();
			for(int i = 0; i < g; i++) 
				finalSolution = finalSolution + solution.dequeue();

			return finalSolution;
		} 
		catch (StackUnderflowException e) {
			System.err.println("A FORMAT ERROR OCCURED");
			throw new InvalidNotationFormatException("INVALID FORMAT");
		}
		catch(Exception e) {
			System.err.println("SOME OTHER ERROR OCCURED");
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * Accepts a postfix expression as a String and converts it to an infix expression
	 * @param postfix
	 * @return A String representation of the postfix expression as a infix expression
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix(String postfix) 
			throws InvalidNotationFormatException{

		NotationStack<String> stack = new NotationStack<String>(postfix.length());

		try {
			for(int i = 0; i < postfix.length(); i++) {
				if (postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9')
					stack.push(postfix.charAt(i)+"");

				if (postfix.charAt(i) == '+' || postfix.charAt(i) == '-' 
						|| postfix.charAt(i) == '*' || postfix.charAt(i) == '/') {

					/*
					 * Takes the first two values off of the stack,
					 * turns them into an infix expression (with parenthesis),
					 * and puts that resulting String into the stack.
					 */
					String secondValue = stack.pop();
					String firstValue = stack.pop();
					String str = "(" + firstValue + postfix.charAt(i) 
					+ secondValue + ")";
					stack.push(str);
				}
			}	

			/*
			 * If there is more than one element on the stack, then
			 * that is caused by bad formatting
			 */
			if(stack.size() > 1) 
				throw new InvalidNotationFormatException("INVALID FORMAT");

			//The result should be the only thing on the stack
			return stack.pop();
		}
		catch (StackUnderflowException e) {
			System.err.println("A FORMAT ERROR OCCURED");
			throw new InvalidNotationFormatException("INVALID FORMAT");
		}
		catch(Exception e) {
			System.err.println("SOME OTHER ERROR OCCURED");
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * Evaluates a postfix expression
	 * @param postfix
	 * @return The evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String postfix)
			throws InvalidNotationFormatException{
		NotationStack<Double> stack = new NotationStack<Double>(postfix.length());

		try {
			for(int i = 0; i < postfix.length(); i++) {
				if (postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9')
					stack.push(Double.parseDouble(postfix.charAt(i)+""));

				if (postfix.charAt(i) == '+' || postfix.charAt(i) == '-' 
						|| postfix.charAt(i) == '*' || postfix.charAt(i) == '/') {

					/*
					 * Takes the first two values off of the stack,
					 * evaluates them as an infix expression,
					 * and puts that resulting Double into the stack.
					 */
					double secondValue = stack.pop();
					double firstValue = stack.pop();
					double result = 0;

					switch(postfix.charAt(i)) {
					case '+': result = firstValue + secondValue; break;
					case '-': result = firstValue - secondValue; break;
					case '*': result = firstValue * secondValue; break;
					case '/': result = firstValue / secondValue; break;
					}

					stack.push(result);
				}
			}	

			/*
			 * If there is more than one element on the stack, then
			 * that is caused by bad formatting
			 */
			if(stack.size() > 1) 
				throw new InvalidNotationFormatException("INVALID FORMAT");

			//The result should be the only thing on the stack
			return stack.pop();
		}
		catch (StackUnderflowException e) {
			System.err.println("A FORMAT ERROR OCCURED");
			throw new InvalidNotationFormatException("INVALID FORMAT");
		}
		catch(Exception e) {
			System.err.println("SOME OTHER ERROR OCCURED");
			e.printStackTrace();
		}

		return 0;
	}
}
