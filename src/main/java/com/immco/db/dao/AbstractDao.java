package com.immco.db.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public abstract class AbstractDao<PK extends Serializable, T> {
	private final Class<T> persistentClass;
	@Autowired
	private SessionFactory sessionFactory;

	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public StatelessSession getStatelessSession() {
		return sessionFactory.openStatelessSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	public List<Object[]> executeSelect(String sql, int startRow, int maxResults) {
		StatelessSession statelessSession = getStatelessSession();
		List<Object[]> list = null;
		try {
			if (maxResults <= 0) {
				SQLQuery sqlQuery = statelessSession.createSQLQuery(sql);
				list = sqlQuery.list();
			} else {
				sql = "select * from(select row_.*, rownum rownum_ from ( " + sql + " )  row_) where rownum_ <=#MAX# and rownum_ >#MIN#";
				String max = startRow + maxResults + "";
				String min = startRow + "";
				sql = sql.replaceAll("#MAX#", max);
				sql = sql.replace("#MIN#", min);
				System.out.println(sql);
				SQLQuery sqlQuery = statelessSession.createSQLQuery(sql);
				sqlQuery.setReadOnly(true);
				sqlQuery.setCacheable(false);
				list = sqlQuery.list();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statelessSession.close();
		}
		return list;
	}

	public List<Object[]> executeSelect(String sql, boolean includeHrd) {
		final List<String> hdrList = new ArrayList<>();
		if (includeHrd) {
			getSession().doWork(new Work() {
				@Override
				public void execute(Connection conn) throws SQLException {
					String hdrSql = "SELECT * FROM (" + sql + ") AS XX WHERE 1=2";
					Statement stmt = conn.createStatement();
					ResultSet resultSet = stmt.executeQuery(hdrSql);
					ResultSetMetaData metaData = resultSet.getMetaData();
					int colCount = metaData.getColumnCount();
					for (int ii = 1; ii <= colCount; ii++) {
						String columnClassName = metaData.getColumnName(ii);
						hdrList.add(columnClassName);
					}
				}
			});
		}
		StatelessSession statelessSession = getStatelessSession();
		List<Object[]> list = null;
		try {
			SQLQuery sqlQuery = statelessSession.createSQLQuery(sql);
			sqlQuery.scroll(ScrollMode.FORWARD_ONLY);
			sqlQuery.setReadOnly(true);
			sqlQuery.setCacheable(false);
			list = sqlQuery.list();
			if (includeHrd)
				list.add(0, hdrList.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statelessSession.close();
		}
		return list;
	}

	public Long getReccount(String countSql) {
		StatelessSession statelessSession = getStatelessSession();
		Number number = null;
		try {
			number = (Number) statelessSession.createSQLQuery(countSql).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statelessSession.close();
		}
		return number.longValue();
	}

	public int updateDirectSql(String sql) {
		Session session = getSession();
		int executeUpdate = session.createSQLQuery(sql).executeUpdate();
		return executeUpdate;
	}
}
