package simplf; 

class Environment {
    private AssocList variables = null;
    final Environment enclosing;

    Environment() {
        this.enclosing = null;
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    void define(Token varToken, String name, Object value) {
    
        variables = new AssocList(name, value, variables);
    }

    void assign(Token name, Object value) {
        
        for (AssocList l = variables; l != null; l = l.next) {
            if (l.name.equals(name.lexeme)) {
                l.value = value;
                return;
            }
        }

        
        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        
        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    Object get(Token name) {
        
        for (AssocList l = variables; l != null; l = l.next) {
            if (l.name.equals(name.lexeme)) {
                return l.value;
            }
        }

        
        if (enclosing != null) {
            return enclosing.get(name);
        }

        
        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }
}