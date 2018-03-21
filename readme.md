# start mongo db
sudo  ./dev/db/mongodb-osx-x86_64-2.6.5/bin/mongod

# connect to mongo db via terminal 
./dev/db/mongodb-osx-x86_64-2.6.5/bin/mongo

# useful commands
show dbs; 
show collections;
db.property.find().pretty()
db.property.remove({})