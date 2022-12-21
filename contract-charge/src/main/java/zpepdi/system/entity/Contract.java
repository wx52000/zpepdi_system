package zpepdi.system.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * contract
 * @author 
 */
public class Contract{
    private String id;

    private String number;

    private String name;

    private String firstParty;

    private String businessManage;

    /**
     * 预算金额
     */
    private BigDecimal budget;

    /**
     * 合同金额
     */
    private BigDecimal money;

    private String signDate;

    /**
     * 结算金额
     */
    private BigDecimal settlement;

    /**
     * 收费合同、分包合同、协议合同
     */
    private String attribute;

    /**
     * 工程合同、工程补充合同（变更）、其他合同
     */
    private String property;

    private String type;

    /**
     * 是否境外合同
     */
    private Integer isOffShore;

    /**
     * 已签、未签、不签、中止、注销、呆账
     */
    private String state;

    /**
     * 已清、未清
     */
    private String completeState;

    private String sponsorDep;

    private String classes;

    /**
     * 0 设计合同 1 总承包
     */
    private Integer kind;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getBusinessManage() {
        return businessManage;
    }

    public void setBusinessManage(String businessManage) {
        this.businessManage = businessManage;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public BigDecimal getSettlement() {
        return settlement;
    }

    public void setSettlement(BigDecimal settlement) {
        this.settlement = settlement;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsOffShore() {
        return isOffShore;
    }

    public void setIsOffShore(Integer isOffShore) {
        this.isOffShore = isOffShore;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompleteState() {
        return completeState;
    }

    public void setCompleteState(String completeState) {
        this.completeState = completeState;
    }

    public String getSponsorDep() {
        return sponsorDep;
    }

    public void setSponsorDep(String sponsorDep) {
        this.sponsorDep = sponsorDep;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }
}