package factory;

import domain.Strategy;

public interface Factory {
    public Container createContainer(Strategy s);
}

