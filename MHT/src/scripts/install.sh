#!/bin/bash

# installer for ettergui

# install deps
apt-get intall ettercap nmap wget python-twisted-web python gksu

wget http://www.thoughtcrime.org/software/sslstrip/sslstrip-0.9.tar.gz
tar zxvf sslstrip-0.9.tar.gz
cd sslstrip-0.9
python ./setup.py install
cd ..
rm -rf sslstrip-0.9
rm sslstrip-0.9.tar.gz

cp EtterGui.jar /opt

touch $HOME/Desktop/EtterGui.sh
echo "#!/bin/bash" > $HOME/Desktop/EtterGui.sh
echo "gksu java -jar /opt/EtterGui.jar" >> $HOME/Desktop/EtterGui.sh
chmod 777 $HOME/Desktop/EtterGui.sh
