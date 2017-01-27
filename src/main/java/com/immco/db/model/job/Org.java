package com.immco.db.model.job;

import java.math.BigDecimal;

import javax.validation.constraints.Size;

import com.immco.db.model.IPersistable;

/**
 */
public class Org implements IPersistable
{
    public static final String PKEY="pKey";
    public static final String ORG_LEVEL="orgLevel";
    public static final String RGN_NAME="rgnName";
    public static final String MA_NAME="maName";
    public static final String MA_CD="maCd";
    public static final String GLID_NO="glidNo";
    public static final String GLID_NAME="glidName";
    public static final String PSID_NO="psidNo";
    public static final String PSID_NAME="psidName";
    public static final String DISCRIMINATOR="discriminator";
    
    private BigDecimal pKey;
    private Integer orgLevel;
    private String rgnName;
    private String maName;
    private String maCd;
    private Integer glidNo;
    private String glidName;
    private Integer psidNo;
    private String psidName;
    private String discriminator;

    public BigDecimal getpKey()
    {
	return pKey;
    }

    public Integer getOrgLevel()
    {
	return orgLevel;
    }

    public String getRgnName()
    {
	return rgnName;
    }

    public String getMaName()
    {
	return maName;
    }

    public String getMaCd()
    {
	return maCd;
    }

    public Integer getGlidNo()
    {
	return glidNo;
    }

    public String getGlidName()
    {
	return glidName;
    }

    public Integer getPsidNo()
    {
	return psidNo;
    }

    public String getPsidName()
    {
	return psidName;
    }

    public String getDiscriminator()
    {
	return discriminator;
    }

    public void setpKey(BigDecimal pKey)
    {
	this.pKey = pKey;
    }

    public void setOrgLevel(Integer orgLevel)
    {
	this.orgLevel = orgLevel;
    }

    public void setRgnName(String rgnName)
    {
	this.rgnName = rgnName;
    }

    public void setMaName(String maName)
    {
	this.maName = maName;
    }

    public void setMaCd(String maCd)
    {
	this.maCd = maCd;
    }

    public void setGlidNo(Integer glidNo)
    {
	this.glidNo = glidNo;
    }

    public void setGlidName(String glidName)
    {
	this.glidName = glidName;
    }

    public void setPsidNo(Integer psidNo)
    {
	this.psidNo = psidNo;
    }

    public void setPsidName(String psidName)
    {
	this.psidName = psidName;
    }

    public void setDiscriminator(String discriminator)
    {
	this.discriminator = discriminator;
    }

}
