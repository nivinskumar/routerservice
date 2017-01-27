package com.immco.db.proxy;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.immco.common.JsonWrapper;
import com.immco.db.remote.ConstructionEndPoints.BASICCONTROLLER;
import com.immco.db.remote.ConstructionServiceConfig;

@Service(value = "constructionServiceProxy")
public class ConstructionServiceProxy {
	private static final String USERID_PLACEHOLDER = "{auditableUserId}";

	private String baseUrl;
	private String dbServiceCtx;

	private BasicAuthRestTemplate rt;

	@Autowired
	private JsonWrapper jsonWrapper;

	@Autowired
	private ConstructionServiceConfig constructionServiceConfig;

	@PostConstruct
	private void configure() {
		this.rt = new BasicAuthRestTemplate(constructionServiceConfig.getSecurityUserName(),
				constructionServiceConfig.getSecurityPassword());
		this.baseUrl = constructionServiceConfig.getBaseUrl();
		this.dbServiceCtx = constructionServiceConfig.getDbServiceCtx();
	}

	public <T> T createOrUpdateObject(String url, DCParam findParam, String loggedInUserId, Class<T> valueType)
			throws Exception {
		StringBuffer sb = new StringBuffer(baseUrl).append(dbServiceCtx).append(url);
		url = sb.toString().replace(USERID_PLACEHOLDER, loggedInUserId);
		String json = jsonWrapper.toJSON(findParam);
		// System.out.println(url);
		json = (String) rt.postForObject(url, json, String.class);
		T t = jsonWrapper.fromGSON(json, valueType);
		return t;
	}

	public <T> T findObject(String url, DCParam findParam, String loggedInUserId, Class<T> valueType) throws Exception {
		StringBuffer sb = new StringBuffer(baseUrl).append(dbServiceCtx).append(url);
		url = sb.toString().replace(USERID_PLACEHOLDER, loggedInUserId);
		String json = jsonWrapper.toJSON(findParam);
		json = (String) rt.postForObject(url, json, String.class);
		T t = jsonWrapper.fromJSON(json, valueType);
		return t;
	}

	public <T> List<T> findAllObjects(String url, DCParam findParam, String loggedInUserId, Class<T> valueType)
			throws Exception {
		StringBuffer sb = new StringBuffer(baseUrl).append(dbServiceCtx).append(url);
		url = sb.toString().replace(USERID_PLACEHOLDER, loggedInUserId);
		String json = jsonWrapper.toJSON(findParam);
		json = (String) rt.postForObject(url, json, String.class);
		return findAllObjects(json, valueType);
	}

	private <T> List<T> findAllObjects(String json, Class<T> t) throws Exception {
		return jsonWrapper.fromGSONAsList(json, t);
	}

	public PaginationConfig executePagedSelect(String loggedInUserId, PaginationConfig pgConfig) throws Exception {
		pgConfig.setResultSet(null);
		StringBuffer sb = new StringBuffer(baseUrl).append(dbServiceCtx).append(BASICCONTROLLER.BASE_URL)
				.append(BASICCONTROLLER.EXECUTE_SQL_PGCONFIG);
		String url = sb.toString().replace(USERID_PLACEHOLDER, loggedInUserId);
		String json = jsonWrapper.toJSON(pgConfig);
		json = rt.postForObject(url, json, String.class);
		pgConfig = jsonWrapper.fromJSON(json, PaginationConfig.class);
		return pgConfig;
	}

	/**
	 * Use this api to get results from the db directly. The returned type is a
	 * List of Object[]
	 * 
	 * @author shajeelawrence
	 * 
	 */
	public List<?> executePlainSelect(String loggedInUserId, String sql) throws Exception {

		SqlResultLoader sqlResultLoader = new SqlResultLoader();
		sqlResultLoader.setSql(sql);
		try {
			StringBuffer sb = new StringBuffer(baseUrl).append(dbServiceCtx).append(BASICCONTROLLER.BASE_URL)
					.append(BASICCONTROLLER.EXECUTE_RAW_SQL);
			String url = sb.toString().replace(USERID_PLACEHOLDER, loggedInUserId);
			String json = jsonWrapper.toJSON(sqlResultLoader);
			json = rt.postForObject(url, json, String.class);
			sqlResultLoader = jsonWrapper.fromJSON(json, SqlResultLoader.class);
			return sqlResultLoader.getResultSet();
		} catch (ResourceAccessException e) {
			throw e;
		}
	}

	/**
	 * This api will soon be removed. Please use executePlainSelect(String
	 * loggedInUserId, String sqlString) instead
	 * 
	 * @param loggedInUserId
	 * @param pgConfig
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public PaginationConfig executePlainSelect(String loggedInUserId, PaginationConfig pgConfig) throws Exception {
		try {
			pgConfig.setResultSet(null);
			StringBuffer sb = new StringBuffer(baseUrl).append(dbServiceCtx).append(BASICCONTROLLER.BASE_URL)
					.append(BASICCONTROLLER.EXECUTE_SQL_PLAIN);
			String url = sb.toString().replace(USERID_PLACEHOLDER, loggedInUserId);
			String json = jsonWrapper.toJSON(pgConfig);
			json = rt.postForObject(url, json, String.class);
			pgConfig = jsonWrapper.fromJSON(json, PaginationConfig.class);
			return pgConfig;
		} catch (ResourceAccessException e) {
			throw e;
		}
	}

	public <T> List<T> fromGSONAsList(String json, Class<T> t)
			throws IOException, JsonParseException, JsonMappingException {
		return jsonWrapper.fromGSONAsList(json, t);
	}

	public int executePlainUpdate(String loggedInUserId, String sql) throws Exception {

		SqlResultLoader sqlResultLoader = new SqlResultLoader();
		sqlResultLoader.setSql(sql);
		try {
			StringBuffer sb = new StringBuffer(baseUrl).append(dbServiceCtx).append(BASICCONTROLLER.BASE_URL)
					.append(BASICCONTROLLER.EXECUTE_RAW_UPDATE_SQL);
			String url = sb.toString().replace(USERID_PLACEHOLDER, loggedInUserId);
			String json = jsonWrapper.toJSON(sqlResultLoader);
			json = rt.postForObject(url, json, String.class);
			sqlResultLoader = jsonWrapper.fromJSON(json, SqlResultLoader.class);
			return sqlResultLoader.getRecordCount();
		} catch (ResourceAccessException e) {
			throw e;
		}
	}
}