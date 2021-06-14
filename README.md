## InfinityDB Java NoSQL Database Demo

**InfinityDB Embedded:**

-	is a Java NoSQL database, a hierarchical sorted key value store

**InfinityDB Encrypted:**

-	is identical to InfinityDB Embedded but encrypts 100% of the database 100% of the time.

**InfinityDB Client/Server:**

-	provides secure, remote, shared access to multiple InfinityDB Embedded files



InfinityDB has the highest available performance, according to our customers and the provided performance tests:

-   More than 1M ops/sec are typical for multi-threaded insert, delete, and next in cache
-   Multi-core overlapping operations scale almost linearly in thread count
-   Almost all cores are used with many threads
-   Threads use fair scheduling, with very low inter-thread interference
-   Random I/O scales logarithmically in file size, with no size limit
-   Huge caches are efficient – 1MB to 100GB or more, and are on-heap
-   Caches grow only as used, and are packed efficiently
-   Transactions are fast: 50/s on disk, 300/s on flash, or thousands/sec for delayed durability
-   Database open is immediate, even for recovery after abrupt exit
-   InfinityDB Embedded is easy to use:

The entire database is in a single file, used by a single JVM. The file format never changes.

-   10 simple API calls (insert, delete, delete suffixes, update, first, next, last, previous, commit, rollback)
-   12 basic data types:
  -   String, double, float, long, boolean, date, byte array, byte string, char array, index, ‘Class’, ‘Attribute’
-   Unlimited nestable data structures and patterns:
-   BLOBs, CLOBs, trees, documents, inversions, lists, maps, sets, tuples, elegant EAV
-   Schema is dynamically extensible but can be backwards compatible
-   Data compression up to 10x or more, continuous, no memory or storage leaks
-   APIs: fast ‘ItemSpace’, fast ConcurrentNavigableMap adapter, and JSON 
-   Zero administration


### Evaluate InfinityDB


<table border="1">
  <tbody>
    <tr>
      <th>Feature</th>
      <th align="center">Details</th>
    </tr>
    <tr>
      <td>Embedded</td>
      <td> 
      </td>
    </tr>
    <tr>
      <td>Data </td>
      <td>
      </td>
    </tr>
    <tr>
      <td>Security</td>
      <td>
      </td>
    </tr>
    <tr>
      <td>Scalable</td>
      <td> 
      	<a href="https://github.com/nitrite/nitrite-jmh/tree/master/nitrite-v3">Benchmark Reference</a>
      </td>
    </tr>
    <tr>
      <td>Replication</td>
      <td>     
      </td>
    </tr>
    <tr>
      <td>Recovery</td>
      <td>	      		
			
Export data to a file


Import data from the file

 	   </td>
    </tr>
  </tbody>
</table>


### Example REST APIs

Simple model

```
http://localhost:8080/api/nodes
{
	"id": 1,
	"holderId": 1,
	"role": "doctor"
}
```

```
GET http://localhost:8080/api/nodes
[
	{
	    "id": 1,
	    "eventId": null,
	    "createdDateTime": null,
	    "lastModifiedDateTime": null,
	    "role": "doctor",
	    "description": null,
	    "status": null,
	    "holder_id": null,
	    "bc_address": null,
	    "cw_id": null,
	    "ec_public_key": null,
	    "role_type": null,
	    "group_id": null,
	    "smart_contract_address": null,
	    "role_class": null,
	    "role_code": null,
	    "role_id": null
	}
]
```

Documents 

Upload

```
POST http://localhost:8080/api/medicalDocuments

Form Data: 
file: <file>
id: 1
```

Download

```
GET http://localhost:8080/api/medicalDocuments/1
```

All Documents

```
GET GET http://localhost:8080/api/medicalDocuments
```