package medium;

import java.util.*;
import java.util.stream.Collectors;

public class AccountsMerge721 {


    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Set<String>>> nameToEmails = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) return result;

        String name = "";
        Set<String> emails = new HashSet<>();
        for (int i = 0; i < accounts.get(0).size(); i++) {
            if (i == 0) {
                name = accounts.get(0).get(i);
            } else {
                emails.add(accounts.get(0).get(i));
            }
        }

        nameToEmails.put(name, new ArrayList<>());
        nameToEmails.get(name).add(emails);
        // First is populated now.
        // Now iterate and merge
        for (int i = 1; i < accounts.size(); i++) {
            String namei = accounts.get(i).get(0);
            Set<String> itsEmails = getEmails(accounts.get(i));
            if (!mergeIfPossible(namei, itsEmails, nameToEmails)) {
                // Otherwise merge now.
                // Note Two different person can have same name so don't wipe out the earlier on if any
                if (nameToEmails.containsKey(namei)) {
                    nameToEmails.get(namei).add(itsEmails);
                } else {
                    // Create a new entry.
                    nameToEmails.put(namei, new ArrayList<>());
                    nameToEmails.get(namei).add(itsEmails);
                }
            }
        }

        for (String keyName : nameToEmails.keySet()) {
            for (Set<String> emailsSetList : nameToEmails.get(keyName)) {
                List<String> keyEmails = new ArrayList<>();
                keyEmails.add(keyName);
                // As per output format we need to sort the emails.
                keyEmails.addAll(emailsSetList.stream().sorted().collect(Collectors.toList()));

                result.add(keyEmails);
            }

        }

        return result;
    }

    private boolean mergeIfPossible(String namei, Set<String> itsEmails, Map<String, List<Set<String>>> nameToEmails) {
        for (String keyName : nameToEmails.keySet()) {
            for (Set<String> keyNameEmails : nameToEmails.get(keyName)) {
                Set<String> commonIfAny = keyNameEmails.stream().filter(itsEmails::contains).collect(Collectors.toSet());
                if (!commonIfAny.isEmpty()) {
                    keyNameEmails.addAll(itsEmails);
                    return true;
                }
            }
        }

        return false;
    }

    private Set<String> getEmails(List<String> emails) {
        Set<String> emailSet = new HashSet<>();
        // Note first is the name actually
        for (int i = 1; i < emails.size(); i++) {
            emailSet.add(emails.get(i));
        }

        return emailSet;
    }

    public static void main(String[] args) {
        AccountsMerge721 accountsMerge721 = new AccountsMerge721();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList(new String[]{"John","johnsmith@mail.com","john_newyork@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John","johnsmith@mail.com","john00@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"Mary","mary@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John","johnnybravo@mail.com"}));

        System.out.println(accountsMerge721.accountsMerge(accounts));

        List<List<String>> accounts1 = new ArrayList<>();
        accounts1.add(Arrays.asList(new String[]{"Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"}));
        accounts1.add(Arrays.asList(new String[]{"Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"}));
        accounts1.add(Arrays.asList(new String[]{"Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"}));
        accounts1.add(Arrays.asList(new String[]{"Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"}));
        accounts1.add(Arrays.asList(new String[]{"Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"}));

        System.out.println(accountsMerge721.accountsMerge(accounts1));

        List<List<String>> accounts2 = new ArrayList<>();
        accounts1.add(Arrays.asList(new String[]{"David","David0@m.co","David1@m.co"}));
        accounts1.add(Arrays.asList(new String[]{"David","David4@m.co","David5@m.co"}));
        accounts1.add(Arrays.asList(new String[]{"David","David3@m.co","David4@m.co"}));
        accounts1.add(Arrays.asList(new String[]{"David","David2@m.co","David3@m.co"}));
        accounts1.add(Arrays.asList(new String[]{"David","David1@m.co","David2@m.co"}));


    }
}


