# JDP Parser
###### Jedicut wing profile file reader / generator 

------------------------------------------------

File structure -

| Section             | Byte                       | Value                       |
|---------------------|----------------------------|-----------------------------|
| Header              | `{0x72, 0xFE, 0x12, 0x01}` | %unreadable%                |
| Row count 'Above'   | `{0x02, 0x00, 0x00, 0x00}` | %hex_value_of_rows_count%   |
| Each Value length   | `{0x04, 0x00, 0x00, 0x00}` | %hex_value_of_value_length% |
| Data by each symbol | `{0x53, 0x00, 0x00, 0x00}` |                             |
|                     |                            |                             |
|                     |                            |                             |
|                     |                            |                             |