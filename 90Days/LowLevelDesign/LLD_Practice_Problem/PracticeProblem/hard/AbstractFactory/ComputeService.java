package PracticeProblem.hard.AbstractFactory;

// Product contracts, concrete products, and the test-facing client are pre-implemented.
// Do not change them; complete only the factory types marked below.

import java.util.ArrayList;
import java.util.List;

interface ComputeService {
    String providerName();

    String launch(String instanceType);
}

interface StorageService {
    String providerName();

    String createBucket(String bucketName);
}

interface DatabaseService {
    String providerName();

    String provision(String engine, String size);
}

class AwsCompute implements ComputeService {
    @Override
    public String providerName() {
        return "aws";
    }

    @Override
    public String launch(String instanceType) {
        return "Launching EC2 instance: " + instanceType;
    }
}

class GcpCompute implements ComputeService {
    @Override
    public String providerName() {
        return "gcp";
    }

    @Override
    public String launch(String instanceType) {
        return "Launching GCE instance: " + instanceType;
    }
}

class AwsStorage implements StorageService {
    @Override
    public String providerName() {
        return "aws";
    }

    @Override
    public String createBucket(String bucketName) {
        return "Creating S3 bucket: " + bucketName;
    }
}

class GcpStorage implements StorageService {
    @Override
    public String providerName() {
        return "gcp";
    }

    @Override
    public String createBucket(String bucketName) {
        return "Creating GCS bucket: " + bucketName;
    }
}

class AwsDatabase implements DatabaseService {
    @Override
    public String providerName() {
        return "aws";
    }

    @Override
    public String provision(String engine, String size) {
        return "Provisioning RDS: " + engine + ", " + size;
    }
}

class GcpDatabase implements DatabaseService {
    @Override
    public String providerName() {
        return "gcp";
    }

    @Override
    public String provision(String engine, String size) {
        return "Provisioning Cloud SQL: " + engine + ", " + size;
    }
}

// Implement the CloudFactory interface here.
interface CloudFactory{
    String providerName();
    ComputeService createCompute();
    StorageService createStorage();
    DatabaseService createDatabase();
}
// Implement the AwsCloudFactory class here.

class AwsCloudFactory implements CloudFactory{

    @Override
    public String providerName(){
        return "aws";
    }

    @Override
    public ComputeService createCompute(){
        return new AwsCompute();
    }

    @Override
    public StorageService createStorage(){
        return new AwsStorage();
    }

    @Override
    public DatabaseService createDatabase(){
        return new AwsDatabase();
    }
}
// Implement the GcpCloudFactory class here.
class GcpCloudFactory implements CloudFactory{
    @Override
    public String providerName(){
        return "gcp";
    }

    @Override
    public ComputeService createCompute(){
        return new GcpCompute();
    }

    @Override
    public StorageService createStorage(){
        return new GcpStorage();
    }

    @Override
    public DatabaseService createDatabase(){
        return new GcpDatabase();
    }
}
// ResourceRequest and CloudDeployment are pre-implemented. Do not change them.
class ResourceRequest {
    int slot;
    String first;
    String second;

    ResourceRequest(int slot, String first, String second) {
        this.slot = slot;
        this.first = first;
        this.second = second;
    }
}

class CloudDeployment {
    private static final int MAX_RESOURCES = 6;

    private String provider;
    private ComputeService compute;
    private StorageService storage;
    private DatabaseService database;
    private List<ResourceRequest> requests = new ArrayList<>();

    public CloudDeployment() {
        useFactory(new AwsCloudFactory());
    }

    private CloudFactory factoryFor(String name) {
        if (name.equals("aws")) {
            return new AwsCloudFactory();
        }
        if (name.equals("gcp")) {
            return new GcpCloudFactory();
        }
        return null;
    }

    private void useFactory(CloudFactory factory) {
        provider = factory.providerName();
        compute = factory.createCompute();
        storage = factory.createStorage();
        database = factory.createDatabase();
    }

    private boolean record(int slot, String first, String second) {
        if (requests.size() >= MAX_RESOURCES) {
            return false;
        }
        requests.add(new ResourceRequest(slot, first, second));
        return true;
    }

    public boolean setProvider(String name) {
        CloudFactory factory = factoryFor(name);
        if (factory == null || !requests.isEmpty()) {
            return false;
        }
        useFactory(factory);
        return true;
    }

    public String currentProvider() {
        return provider;
    }

    public boolean addCompute(String instanceType) {
        return record(0, instanceType, "");
    }

    public boolean addStorage(String bucketName) {
        return record(1, bucketName, "");
    }

    public boolean addDatabase(String engine, String size) {
        return record(2, engine, size);
    }

    public String[] deploy() {
        if (requests.isEmpty()) {
            return new String[]{"NOTHING TO DEPLOY"};
        }
        String[] lines = new String[requests.size()];
        for (int i = 0; i < requests.size(); i++) {
            ResourceRequest r = requests.get(i);
            if (r.slot == 0) {
                lines[i] = compute.launch(r.first);
            } else if (r.slot == 1) {
                lines[i] = storage.createBucket(r.first);
            } else {
                lines[i] = database.provision(r.first, r.second);
            }
        }
        return lines;
    }

    public String partsReport() {
        return "compute=" + compute.providerName()
                + ", storage=" + storage.providerName()
                + ", database=" + database.providerName();
    }

    public boolean reset() {
        if (requests.isEmpty()) {
            return false;
        }
        requests = new ArrayList<>();
        return true;
    }

    public int resourceCount() {
        return requests.size();
    }
}
