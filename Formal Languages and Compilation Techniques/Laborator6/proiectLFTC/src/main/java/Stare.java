import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;
import java.util.Map;


public class Stare {

    private Integer stateId;
    private String element;//terminal sau neterminal
    private MultiValuedMap<String, MutablePair<List<String>, List<String>>> productii;
    private Map<String, Integer> tranzitii;


    public Stare(Integer stateId, String element, MultiValuedMap<String, MutablePair<List<String>, List<String>>> productii, Map<String, Integer> tranzitii) {
        this.stateId = stateId;
        this.element = element;
        this.productii = productii;
        this.tranzitii = tranzitii;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public MultiValuedMap<String, MutablePair<List<String>, List<String>>> getProductii() {
        return productii;
    }

    public void setProductii(MultiValuedMap<String, MutablePair<List<String>, List<String>>> productii) {
        this.productii = productii;
    }

    public Map<String, Integer> getTranzitii() {
        return tranzitii;
    }

    public void setTranzitii(Map<String, Integer> tranzitii) {
        this.tranzitii = tranzitii;
    }

    @Override
    public String toString() {
        return "Stare{" +
                "stateId=" + stateId +
                ", element='" + element + '\'' +
                ", productii=" + productii +
                ", tranzitii=" + tranzitii +
                '}';
    }
}