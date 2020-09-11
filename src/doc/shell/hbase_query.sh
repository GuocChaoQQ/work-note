#!/bin/bash
hbase shell << EOF
scan 'scan 'tablename',{COLUMNS=>'列族','列族'}'

EOF


