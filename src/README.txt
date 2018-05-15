--Write-Up Questions--
Brandon Dixon, TJ Dillon, Sawyere Hendricks

1. "salutation megacosm oneself is scintillate in passage to enscroll golden rule and fob scintilla-
te at punch-card data structures"

2. i. The starting capacity we chose was 13 which is somewhat arbitrary since ultimately we don't
   know what kind of data the object would be using. We chose 13 because it won't have to resize im-
   mediately with smaller data sets but as it gets larger it will have to resize anyway.
   ii. We chose to resize and rehash at a load factor of 1. We decided this because this makes resi-
   sizing relatively rare (at least not before the table reaches capacity) and the amount of collis-
   ions that are likely to happen before the table reaches a load factor of 1 is low. This means th-
   at looking up entries in the hash table is always going to be fast and we won't need to resize v-
   ery frequently.
   iii. We decided to decide the new size of the array based on a hard coded class constant array of
   prime numbers like we discussed in class. To find these numbers we just looked at a very large l-
   ist of prime numbers and found very rough doubles and inserted those.

3. We ended up using the hash function of wordInfo.hashCode % table.length because we decided
that the wordInfo hashCode was good and we mod by table size to keep it within the restraints of
the table. We thought that the wordInfo hashCode was good because it incorporated only the "word"
field's data which is what was relevant to us, utilized the String hashCode function, and multip-
lied by a prime (31) to make collisions rare.

4. If using a strategy other than separate chaining we decided we would use double-hashing. We
don't have a lot of experience to know if this would be a perfect choice for this situation but
double hashing is a very interesting strategy and it seems to strike a good balance between speed
and memory usage.