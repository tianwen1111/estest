import com.alibaba.fastjson.JSONObject;
import com.an.ElasticSearchAppliction;
import com.an.domian.Book;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ElasticSearchAppliction.class})
public class testElasticSearch {


    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Test
    public void test01() throws IOException {

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("book");
       /* Book book = new Book();
        book.setContent("hi");
        book.setTitle("welcome");
        createIndexRequest.source(JSONObject.toJSONString(book), XContentType.JSON);*/
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest);
        System.out.println(JSONObject.toJSONString(createIndexResponse));
    }

    @Test
    public void test02() throws IOException {

        /*GetIndexRequest getIndexRequest = new GetIndexRequest();
        getIndexRequest.indices("book");*/
        OpenIndexRequest openIndexRequest = new OpenIndexRequest();
        openIndexRequest.indices("book");

        OpenIndexResponse openIndexResponse = restHighLevelClient.indices().open(openIndexRequest);

        System.out.println(JSONObject.toJSONString(openIndexResponse));
    }

    @Test
    public void test03() throws IOException {

        DeleteIndexRequest deleteRequest = new DeleteIndexRequest("book");

        DeleteIndexResponse delete = restHighLevelClient.indices().delete(deleteRequest);

        System.out.println(JSONObject.toJSONString(delete));
    }

    @Test
    public void test4() throws IOException {

        // 定义请求对象
        IndexRequest indexRequest = new  IndexRequest("book","book");

        // 设置文档id
        indexRequest.id("10001");
        // 将json格式字符串放在请求中

        Book book = new Book();
        book.setId("10001");
        book.setContent("青丝与白发1");
        book.setTitle("welcome");
        book.setPrice(1d);
        indexRequest.source(JSONObject.toJSONString(book), XContentType.JSON);

        BulkRequest request = new BulkRequest();
        request.add(indexRequest);

        // 3、发送请求到ES
        BulkResponse bulk = restHighLevelClient.bulk(request);

        System.out.println(JSONObject.toJSONString(bulk));
    }

    @Test
    public void test44() throws IOException {

        // 定义请求对象
        IndexRequest indexRequest01 = new  IndexRequest("book","book");

        // 设置文档id
        indexRequest01.id("10002");
        // 将json格式字符串放在请求中

        Book book = new Book();
        book.setId("10002");
        book.setContent("青丝与白发2");
        book.setTitle("welcome");
        book.setPrice(2d);
        indexRequest01.source(JSONObject.toJSONString(book), XContentType.JSON);

        // 定义请求对象
        IndexRequest indexRequest02 = new  IndexRequest("book","book");

        // 设置文档id
        indexRequest02.id("10003");
        // 将json格式字符串放在请求中

        Book book2 = new Book();
        book2.setId("10003");
        book2.setContent("青丝与白发3");
        book2.setTitle("welcome");
        book2.setPrice(3d);
        indexRequest02.source(JSONObject.toJSONString(book2), XContentType.JSON);

        BulkRequest request = new BulkRequest();
        request.add(indexRequest01);
        request.add(indexRequest02);

        // 3、发送请求到ES
        BulkResponse bulk = restHighLevelClient.bulk(request);

        System.out.println(JSONObject.toJSONString(bulk));
    }


    @Test
    public void test5() throws IOException {

        // 定义请求对象
        GetRequest getRequest = new  GetRequest("book","book","10002");
        // 3、发送请求到ES
        GetResponse getResponse = restHighLevelClient.get(getRequest);

        System.out.println(JSONObject.toJSONString(getResponse));
        System.out.println(getResponse.getSource());
    }

    @Test
    public void testSearch1() throws IOException {

        SearchRequest searchRequest = new SearchRequest("book");
        searchRequest.types("book");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", "青1");
        searchSourceBuilder.query(queryBuilder);
        // 3、发送请求到ES
        SearchResponse search = restHighLevelClient.search(searchRequest);

        System.out.println(search);
        System.out.println(JSONObject.toJSONString(search));
        System.out.println(JSONObject.toJSONString(search.getHits()));
        SearchHit[] hits = search.getHits().getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }

    }

    @Test
    public void testSearch2() throws IOException {

        SearchRequest searchRequest = new SearchRequest("book");
        searchRequest.types("book");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "青1");
        searchSourceBuilder.query(matchQuery);
        searchRequest.source(searchSourceBuilder);
        // 3、发送请求到ES
        SearchResponse search = restHighLevelClient.search(searchRequest);

        System.out.println(search);
        System.out.println(JSONObject.toJSONString(search));
        System.out.println(JSONObject.toJSONString(search.getHits()));
        SearchHit[] hits = search.getHits().getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }

    }

    @Test
    public void testSearch3() throws IOException {

        SearchRequest searchRequest = new SearchRequest("book");
        searchRequest.types("book");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("content", "青丝与白发1"));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("title", "welco"));
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("price","2"));
        //boolQueryBuilder.should(QueryBuilders.matchQuery(""));

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        // 3、发送请求到ES
        SearchResponse search = restHighLevelClient.search(searchRequest);

        System.out.println(search);
        System.out.println(JSONObject.toJSONString(search));
        System.out.println(JSONObject.toJSONString(search.getHits()));
        SearchHit[] hits = search.getHits().getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }

    }

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest();
        createIndexRequest.index("books");
        createIndexRequest.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas",1).build());
        String bookMappingJsonString = "{\"properties\":{\"id\":{\"type\":\"keyword\"},\"name\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_max_word\"},\"content\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_max_word\"},\"price\":{\"type\":\"double\"}}}";
        createIndexRequest.mapping("books", bookMappingJsonString, XContentType.JSON);
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest);
        System.out.println(createIndexResponse);
        System.out.println(createIndexResponse.isAcknowledged());
    }
}
