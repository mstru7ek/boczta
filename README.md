
server.port: 8013

# First initialization
java -jar build/libs/boczta-0.0.1-SNAPSHOT.jar --spring.jpa.hibernate.ddl-auto=create-drop

# Normal mode
java -jar build/libs/boczta-0.0.1-SNAPSHOT.jar





[Unit]
Description=boczta

[Service]
SyslogIdentifier=boczta
WorkingDirectory=/opt/boczta/
PIDFile=/opt/boczta/boczta.pid
ExecStart=/opt/boczta/boczta-0.0.1-SNAPSHOT.jar
User=root
Type=simple

[Install]
WantedBy=multi-user.target

