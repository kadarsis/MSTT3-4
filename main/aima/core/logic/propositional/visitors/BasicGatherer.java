package main.aima.core.logic.propositional.visitors;

import main.aima.core.logic.propositional.parsing.PLVisitor;
import main.aima.core.logic.propositional.parsing.ast.ComplexSentence;
import main.aima.core.logic.propositional.parsing.ast.PropositionSymbol;
import main.aima.core.util.SetOps;

import java.util.Set;

/**
 * Super class of Visitors that are "read only" and gather information from an
 * existing parse tree .
 *
 * @param <T> the type of elements to be gathered.
 * @author Ravi Mohan
 */
public abstract class BasicGatherer<T> implements PLVisitor<Set<T>, Set<T>> {

    @Override
    public Set<T> visitPropositionSymbol(PropositionSymbol s, Set<T> arg) {
        return arg;
    }

    @Override
    public Set<T> visitUnarySentence(ComplexSentence s, Set<T> arg) {
        return SetOps.union(arg, s.getSimplerSentence(0).accept(this, arg));
    }

    @Override
    public Set<T> visitBinarySentence(ComplexSentence s, Set<T> arg) {
        Set<T> termunion = SetOps.union(
                s.getSimplerSentence(0).accept(this, arg), s
                        .getSimplerSentence(1).accept(this, arg));
        return SetOps.union(arg, termunion);
    }
}
