package simplf;
 
import java.util.List;

class SimplfFunction implements SimplfCallable {

    private final Stmt.Function declaration;
    private Environment closure;

    SimplfFunction(Stmt.Function declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }

    public void setClosure(Environment environment) {
        this.closure = environment;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        
        Environment environment = new Environment(closure);

       
        for (int i = 0; i < declaration.params.size(); i++) {
            Token paramName = declaration.params.get(i);
            environment.define(paramName, paramName.lexeme, args.get(i));
        }

        
        return interpreter.executeBlock(declaration.body, environment);
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }

}