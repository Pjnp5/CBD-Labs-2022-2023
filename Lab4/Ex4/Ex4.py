from neo4j import GraphDatabase, basic_auth

#function to make the process easier
def query(query:str):
    try: 
        response = str(list(session.run(query)))
    except Exception as e:
        print("Query failed:", e)
        exit(1)
    return response + "\n\n"


# Establish a connection to the database
driver = GraphDatabase.driver("bolt://localhost:7687")
session = driver.session()
    

# Import the dataset
#session.run("load csv with headers from 'file:///players.csv' as row merge (p: Player{name: row.name, height: row.height, career_WS: row.career_WS, career_TRB: row.career_TRB, career_PTS: row.career_PTS, career_PER: row.career_PER, career_G: row.career_G, career_FT:row.career_FT, career_FG:row.career_FG, career_AST:row.career_AST, draft_team: row.draft_team, draft_round: row.draft_round, draft_pick: row.draft_pick,draft_year: row.draft_year , _id:row._id }) merge (h: Hand{shoots: row.shoots}) merge (pos: Position {position: row.position}) merge (c: College{ college: row.college}) merge(t: Draft_Team{name: row.draft_team}) merge (p)-[:DRAFTED_BY]->(t) merge (p) - [:UNIVERSITY] -> (c) merge (p) - [:SHOOTS_WITH] -> (h) merge (p) - [:PLAYS_AS] -> (pos)")


#Create/open file
file =open("CBD_L44c_output.txt","w")  

#Do queries

file.write("#1-> Que jogadores draftados pelos Golden State Warriors têm uma percentagem de lançamentos concretizados superior a 60%?\n")
file.write('match (n:Player) - [r:DRAFTED_BY] -> (m:Draft_Team {name:"Golden State Warriors"}) where toFloat(n.career_FG) > 60  return n.name, n.career_FG\n')
file.write(query('match (n:Player) - [r:DRAFTED_BY] -> (m:Draft_Team {name:"Golden State Warriors"}) where toFloat(n.career_FG) > 60  return n.name, n.career_FG'))

file.write("#2-> Jogadores draftados pela mesma equipa (não podem ser undrafted, que é assinalado como '-') onde o primeiro tem como primeiro nome Eric e o segundo como ultimo nome Roberts?\n")
file.write('match (n:Player ) - [r:DRAFTED_BY] -> (t:Draft_Team ) <- [r2:DRAFTED_BY] - (m:Player) where n.name Starts with "Eric " and m.name ends with "Roberts" and t.name <> "-" return n.name, m.name, t.name\n')
file.write(query('match (n:Player ) - [r:DRAFTED_BY] -> (t:Draft_Team ) <- [r2:DRAFTED_BY] - (m:Player) where n.name Starts with "Eric " and m.name ends with "Roberts" and t.name <> "-" return n.name, m.name, t.name'))

file.write("#3-> Retorna os Top 10 de jogadores que foram draftados pelos Boston Celtics na primeira ronda e jogaram pela unversidade de north carolina com maior número de pontos por jogos\n")
file.write('MATCH (p:Player)-[:DRAFTED_BY]->(t:Draft_Team), (p)-[:UNIVERSITY]->(c:College) WHERE p.draft_round starts with "1" AND t.name = "Boston Celtics" AND c.college = "University of North Carolina" RETURN p ORDER BY p.career_PTS  DESC LIMIT 10\n')
file.write(query('MATCH (p:Player)-[:DRAFTED_BY]->(t:Draft_Team), (p)-[:UNIVERSITY]->(c:College) WHERE p.draft_round starts with "1" AND t.name = "Boston Celtics" AND c.college = "University of North Carolina" RETURN p ORDER BY p.career_PTS  DESC LIMIT 10'))

file.write("#4-> Avg de pontos dos Jogadores que foram draftados por certa equipa e têm mão dominante tal (todas as combinações) ordenados por avg de pontos\n")
file.write('MATCH (p:Player)-[:UNIVERSITY]->(c:College), (p)-[:SHOOTS_WITH]->(h:Hand), (p)-[:DRAFTED_BY]->(t:Draft_Team) RETURN h.shoots, t.name, AVG(toFloat(p.career_PTS)) order by AVG(toFloat(p.career_PTS)) desc\n')
file.write(query('MATCH (p:Player)-[:UNIVERSITY]->(c:College), (p)-[:SHOOTS_WITH]->(h:Hand), (p)-[:DRAFTED_BY]->(t:Draft_Team) RETURN h.shoots, t.name, AVG(toFloat(p.career_PTS)) order by AVG(toFloat(p.career_PTS)) desc'))

file.write("#5-> Os 10 Point Guards puros com mais pontos no total:\n")
file.write('MATCH (p:Player)-[:PLAYS_AS]->(pos:Position) WHERE pos.position = "Point Guard" RETURN p.name, round(toFloat(p.career_PTS) * toFloat(p.career_G)) as total_point ORDER BY total_point  DESC LIMIT 10\n')
file.write(query('MATCH (p:Player)-[:PLAYS_AS]->(pos:Position) WHERE pos.position = "Point Guard" RETURN p.name, round(toFloat(p.career_PTS) * toFloat(p.career_G)) as total_point ORDER BY total_point  DESC LIMIT 10'))


file.write("#6-> Todsos os jogadores draftados com as picks 10, 11 e 12 depois de 2000\n")
file.write('MATCH (p:Player)-[:PLAYS_AS]->(pos:Position) WHERE 10 <= toFloat(substring(p.draft_pick, 0, 2)) <= 12 and toFloat(p.draft_year) > 2000 RETURN p.name as Name, p.draft_pick as Draft_Pick, p.draft_year as Draft_Year ORDER BY p.draft_pick  DESC\n')
file.write(query('MATCH (p:Player)-[:PLAYS_AS]->(pos:Position) WHERE 10 <= toFloat(substring(p.draft_pick, 0, 2)) <= 12 and toFloat(p.draft_year) > 2000 RETURN p.name as Name, p.draft_pick as Draft_Pick, p.draft_year as Draft_Year ORDER BY p.draft_pick  DESC'))

file.write("#7-> Repita a alinea anterior mas diga também quais passaram pela universidade e quais não passaram\n")
file.write('MATCH (p:Player) WHERE 10 <= toFloat(substring(p.draft_pick, 0, 2)) <= 12 and toFloat(p.draft_year) > 2000 RETURN p.name as Name, p.draft_pick as Draft_Pick, p.draft_year as Draft_Year, exists((p)-[:UNIVERSITY]->()) AS was_at_COLLEGE ORDER BY p.draft_pick  DESC\n')
file.write(query('MATCH (p:Player) WHERE 10 <= toFloat(substring(p.draft_pick, 0, 2)) <= 12 and toFloat(p.draft_year) > 2000 RETURN p.name as Name, p.draft_pick as Draft_Pick, p.draft_year as Draft_Year, exists((p)-[:UNIVERSITY]->()) AS was_at_COLLEGE ORDER BY p.draft_pick  DESC'))

file.write("#8-> Quantos jogadores já foram draftados em cada ronda (hoje em dia só existe 1 e segunda ronda, mas já houve mais e versões diferentes), Os jogadores não draftados aparecem com -\n")
file.write('MATCH (p:Player) where p.draft_round <> "-" RETURN p.draft_round, COUNT(p) order by count(p) desc\n')
file.write(query('MATCH (p:Player) where p.draft_round <> "-" RETURN p.draft_round, COUNT(p) order by count(p) desc'))

 
file.write("#9-> Lista todos os jogadores que têm mais de 10000 rebounds e mais de 5000 assistencias na carreira:\n")
file.write('MATCH (p:Player) with round(toFloat(p.career_AST) * toFloat(p.career_G)) as Total_AST,  round(toFloat(p.career_TRB)  * toFloat(p.career_G)) as Total_TRB , p.name as Name where Total_AST > 5000 and Total_TRB > 10000 RETURN Name, Total_TRB, Total_AST order by Total_TRB, Total_AST desc\n')
file.write(query('MATCH (p:Player) with round(toFloat(p.career_AST) * toFloat(p.career_G)) as Total_AST,  round(toFloat(p.career_TRB)  * toFloat(p.career_G)) as Total_TRB , p.name as Name where Total_AST > 5000 and Total_TRB > 10000 RETURN Name, Total_TRB, Total_AST order by Total_TRB, Total_AST desc'))

file.write("#10-> Quantos jogadores lançam com cada mão?\n")
file.write('MATCH (p:Player)-[:SHOOTS_WITH]->(h:Hand) RETURN h.shoots AS hand, COUNT(p) AS count\n')
file.write(query('MATCH (p:Player)-[:SHOOTS_WITH]->(h:Hand) RETURN h.shoots AS hand, COUNT(p) AS count'))

print("Made everything :)")

# Close the session
session.close()