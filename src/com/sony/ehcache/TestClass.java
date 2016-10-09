package com.sony.ehcache;

import java.io.IOException;
import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

public class TestClass {

	public static void main(String[] args) throws IOException {
		InputStream in = TestClass.class.getResourceAsStream("/ehcache.xml");
		CacheManager manager = CacheManager.create(in);
		// System.out.println(manager.getCacheNames()[0]);

		Cache cache = manager.getCache("data-cache");
		/*
		 * System.out.println(cache.getName());
		 * System.out.println(cache.getSize());
		 * System.out.println(cache.getCacheConfiguration
		 * ().getCacheLoaderTimeoutMillis());
		 * System.out.println(cache.getCacheConfiguration
		 * ().getTimeToIdleSeconds());
		 * System.out.println(cache.getCacheConfiguration
		 * ().getTimeToLiveSeconds());
		 * System.out.println(cache.getCacheConfiguration
		 * ().getMaxEntriesInCache());
		 * System.out.println(cache.getStatus().toString());
		 */
		/*manager.removeAllCaches();
		manager.clearAll();
		manager.removeCache("");*/
		
		Element e = new Element("name", "cena");
		cache.put(e);
		
		e = new Element("nickname", "27");
		cache.put(e);
		//Element ele = cache.get("name");
		//System.out.println(ele.getObjectKey() + "-" + ele.getObjectValue());

		Query query = cache.createQuery();
		query.includeKeys();
		query.includeValues();
		//Attribute<String> keyAttr= cache.getSearchAttribute("key");
		//query.addCriteria(keyAttr.ilike("*name"));
		Attribute<String> valueAttr = cache.getSearchAttribute("value");
		query.addCriteria(valueAttr.eq("cena"));
		Results results = query.execute();
		for(Result r : results.all()){
			System.out.println(r.getKey() + "-" + r.getValue());
		}
	}

}
