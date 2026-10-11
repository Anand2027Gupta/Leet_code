# Write your MySQL query statement below
SELECT
w.id
FROM Weather w
INNER JOIN 
Weather e
ON DATEDIFF(w.recordDate,e.recordDate)=1
WHERE w.temperature>e.temperature;