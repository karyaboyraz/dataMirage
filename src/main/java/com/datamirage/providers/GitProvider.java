package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.util.DataLoader;
import com.datamirage.util.LazyLoader;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * A provider class for generating Git-related data.
 * This class provides methods to generate various Git-related information such as
 * branch names, commit SHAs, commit messages, dates, and complete commit entries.
 */
public class GitProvider {
    private final RandomService random;
    private List<String> commitMessages;
    private List<String> gitAuthors;
    private List<String> gitEmails;

    /**
     * Constructs a new GitProvider with the specified RandomService.
     *
     * @param random The RandomService instance to use for generating random values
     */
    public GitProvider(RandomService random) {
        this.random = random;
    }

    /**
     * Generates a random Git branch name.
     * The branch name consists of lowercase letters, numbers, and hyphens,
     * and is between 3 and 20 characters long.
     *
     * @return A random Git branch name as a string
     */
    public String branch() {
        StringBuilder result = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789-";
        
        // Branch adı en az 3 karakter olmalı
        int length = random.nextInt(3, 20);
        
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt(random.nextInt(0, chars.length() - 1)));
        }
        
        return result.toString();
    }

    /**
     * Generates a random Git commit SHA.
     * The SHA is a 40-character hexadecimal string.
     *
     * @return A random Git commit SHA as a string
     */
    public String commitSha() {
        StringBuilder sha = new StringBuilder();
        String hexChars = "0123456789abcdef";
        
        for (int i = 0; i < 40; i++) {
            sha.append(hexChars.charAt(random.nextInt(0, hexChars.length() - 1)));
        }
        
        return sha.toString();
    }

    /**
     * Generates a random Git commit message.
     *
     * @return A random Git commit message as a string
     */
    public String commitMessage() {
        commitMessages = LazyLoader.load("gitCommitMessages", () -> DataLoader.getListData("git", "commit_messages"));
        return random.randomElement(commitMessages);
    }

    /**
     * Generates a formatted Git commit date.
     * The date is in the format: "EEE MMM d HH:mm:ss yyyy Z"
     *
     * @return A formatted Git commit date as a string
     */
    public String commitDate() {
        ZonedDateTime now = ZonedDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy Z"));
    }

    /**
     * Generates a complete Git commit entry.
     * The entry includes the commit SHA, author name, author email, commit date, and commit message.
     *
     * @return A complete Git commit entry as a string
     */
    public String commitEntry() {
        String sha = commitSha();
        gitAuthors = LazyLoader.load("gitAuthors", () -> DataLoader.getListData("git", "git_authors"));
        String author = random.randomElement(gitAuthors);
        gitEmails = LazyLoader.load("gitEmails", () -> DataLoader.getListData("git", "git_emails"));
        String email = random.randomElement(gitEmails);
        String date = commitDate();
        String message = commitMessage();

        return String.format("commit %s | Author: %s <%s> | Date: %s | %s",
            sha,
            author,
            email,
            date,
            message
        );
    }

    /**
     * Main method for testing the functionality of GitProvider.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        GitProvider gitProvider = new GitProvider(new RandomService());
        System.out.println("Branch: " + gitProvider.branch());
        System.out.println("Commit SHA: " + gitProvider.commitSha());
        System.out.println("Commit Message: " + gitProvider.commitMessage());
        System.out.println("Commit Date: " + gitProvider.commitDate());
        System.out.println("Commit Entry: " + gitProvider.commitEntry());
    }
}