CREATE KEYSPACE keyspace_test
WITH replication = {
	'class' : 'SimpleStrategy',
	'replication_factor' : 1
};

create ColumnFamily keyspace_test.report (
	id UUID primary key,
	name text
); 


select JSON * from  keyspace_test.report;

insert into keyspace_test.report (id, name) values (82b3e3d9-84c4-4d01-bdc3-38802b09d4ba, 'test');
