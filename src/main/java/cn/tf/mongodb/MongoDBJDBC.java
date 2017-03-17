package cn.tf.mongodb;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBJDBC {
	
	
	public static void main(String[] args) {
		try {
			MongoClient  mongoClient=new MongoClient("127.0.0.1",27017);

			//���ӵ����ݿ�
			MongoDatabase  mongoDatabase=mongoClient.getDatabase("tf");
			System.out.println("���ӳɹ�");
			
			//mongoDatabase.createCollection("test");
			//System.out.println("�������ϳɹ�");
			
			//��ȡ����
			MongoCollection<Document> collection = mongoDatabase.getCollection("test");
			
			//�����ĵ�
			createDocument(collection);
			
			//��ѯ�ĵ�
			findIterable(collection);
			
			//�����ĵ�
			//updateMany(collection);
			
			//ɾ���ĵ�
			//deleteMany(collection);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	
	
	//�����ĵ�
	public static void createDocument(MongoCollection<Document>  collection){
		
		Document  document=new Document("title","MongoDB")
		     .append("description", "database")
		     .append("likes", 100)
		     .append("by", "Fly");
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(document);
		collection.insertMany(documents);
		System.out.println("�ĵ�����ɹ�");
		
	}
	
	//��ѯ�ĵ�
	public  static void  findIterable(MongoCollection<Document>  collection){
		FindIterable<Document>  findIterable=collection.find();
		MongoCursor<Document> iterator = findIterable.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	
	
	
	//�����ĵ�
	public  static void  updateMany(MongoCollection<Document>  collection){
		collection.updateMany(Filters.eq("likes",100),new Document("$set",new Document("likes",200))); 
		
		//��ѯ�ĵ�
		findIterable(collection);
	}
	
	//ɾ��
	public  static void deleteMany(MongoCollection<Document>  collection){
		//ɾ�����������ĵ�һ���ĵ�
		
		collection.deleteOne(Filters.eq("likes",200));
		
		
		//ɾ�����з����������ĵ�
		collection.deleteMany(Filters.eq("likes",200));
		
		//��ѯ�ĵ�
		findIterable(collection);
		
		
		
	}
	

}
