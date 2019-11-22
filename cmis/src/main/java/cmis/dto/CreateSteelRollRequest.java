package cmis.dto;

import cmis.entity.UserEntity;

import java.math.BigDecimal;

public class CreateSteelRollRequest {
    private BigDecimal price;
    private String remark;

    public BigDecimal getPrice() {
        return price;
    }

    public String getRemark() {
        return remark;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
