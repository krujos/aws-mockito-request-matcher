#Mockito matcher for AWS Request Objects

A matcher that makes ```verify``` and ```when``` a little more readable when dealing with AWS requests. 

You could do this: 

```
verify(ec2Client).deleteSnapshot(
	new DeleteSnapshotRequest().withSnapshotId("test_snapshot"));
```

but this reads a little easier

```
verify(ec2Client).deleteSnapshot(
	awsRqst(r -> r.getSnapshotId().equals("test_snapshot")));
```

or this:


```
@Test
public void itShouldTerminateTheInstanceUponUnbind() {

	aws.terminateEc2Instance("test_instance");
	
	verify(ec2Client).terminateInstances(argThat(
		new ArgumentMatcher<TerminateInstancesRequest>() {

			@Override
			public boolean matches(Object argument) {
				return ((TerminateInstancesRequest) argument)
					.getInstanceIds().get(0)
					.equals("test_instance");
			}

		})
	);
}
```

could be this:  

```
@Test
public void itShouldTerminateTheInstanceUponUnbind() {

	aws.terminateEc2Instance("test_instance");

	verify(ec2Client).terminateInstances(
		awsRqst(r -> r.getInstanceIds().get(0).equals("test_instance")));
}
```

#Use it: 
Use [JitPack](https://www.jitpack.io/#krujos/aws-mockito-request-matcher/) to add the matcher to your project. 

````
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
````

```
	<dependency>
	    <groupId>com.github.krujos</groupId>
	    <artifactId>aws-mockito-request-matcher</artifactId>
	    <version>v0.1</version>
	</dependency>
```

Special thanks to the folks at [Coveo](http://source.coveo.com/2014/10/01/java8-mockito/) for having implemented and written about this. 
