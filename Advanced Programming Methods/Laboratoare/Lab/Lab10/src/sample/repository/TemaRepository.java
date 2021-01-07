package sample.repository;


import sample.domain.Tema;

/**
 * TemaRepository - repository that stores objects of Tema type
 * extends AbstractRepository - ID=Integer, E=Tema
 */
public class TemaRepository extends AbstractRepository<Integer, Tema> {
    public TemaRepository(Validator<Tema> v){
        super(v);
    }
}
