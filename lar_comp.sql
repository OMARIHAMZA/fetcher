-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 18, 2019 at 12:06 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lar_comp`
--

-- --------------------------------------------------------

--
-- Table structure for table `branches`
--

CREATE TABLE `branches` (
  `id` int(10) UNSIGNED NOT NULL,
  `company_id` int(10) UNSIGNED NOT NULL,
  `address` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `branches`
--

INSERT INTO `branches` (`id`, `company_id`, `address`, `created_at`, `updated_at`) VALUES
(1, 2, 'Istanbul ,Turkey', '2019-03-15 19:20:04', '2019-03-15 19:20:04'),
(2, 2, 'Dubai, UAE', '2019-03-15 19:25:16', '2019-03-15 19:25:16');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `photo`, `created_at`, `updated_at`) VALUES
(1, 'Business', 'https://image.flaticon.com/icons/svg/912/912316.svg', '2019-03-16 09:47:50', '2019-03-16 09:47:50'),
(2, 'Services', 'https://image.flaticon.com/icons/svg/771/771601.svg', '2019-03-16 09:47:50', '2019-03-16 09:47:50'),
(3, 'Medic', 'https://image.flaticon.com/icons/svg/1021/1021799.svg', '2019-03-16 09:47:50', '2019-03-16 09:47:50'),
(4, 'Engineering', 'https://image.flaticon.com/icons/svg/942/942833.svg', '2019-03-16 09:47:50', '2019-03-16 09:47:50'),
(5, 'Maintenance', 'https://image.flaticon.com/icons/svg/1098/1098420.svg', '2019-03-16 09:47:50', '2019-03-16 09:47:50'),
(6, 'Office', 'https://image.flaticon.com/icons/svg/942/942803.svg', '2019-03-16 09:47:50', '2019-03-16 09:47:50'),
(7, 'Security', 'https://image.flaticon.com/icons/svg/1022/1022382.svg', '2019-03-16 09:47:50', '2019-03-16 09:47:50');

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

CREATE TABLE `companies` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `website` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `official_email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `main_address` text COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `companies`
--

INSERT INTO `companies` (`id`, `user_id`, `name`, `website`, `official_email`, `main_address`, `created_at`, `updated_at`) VALUES
(2, 13, 'InMood', 'inmood.net', 'mail@inmood.com', 'Mazzeh , Western Villas - Tala Bridge St.', '2019-03-15 11:25:05', '2019-03-15 11:52:36');

-- --------------------------------------------------------

--
-- Table structure for table `company_photos`
--

CREATE TABLE `company_photos` (
  `id` int(10) UNSIGNED NOT NULL,
  `company_id` int(10) UNSIGNED NOT NULL,
  `photo` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `company_photos`
--

INSERT INTO `company_photos` (`id`, `company_id`, `photo`, `created_at`, `updated_at`) VALUES
(3, 2, 'CompanyPhotos/tunXqH7WaW8Bzu8CYrSqJMamJTqMx2r2KqWJciVg.jpeg', '2019-03-15 20:04:47', '2019-03-15 20:04:47');

-- --------------------------------------------------------

--
-- Table structure for table `job_opportunities`
--

CREATE TABLE `job_opportunities` (
  `id` int(10) UNSIGNED NOT NULL,
  `company_id` int(10) UNSIGNED NOT NULL,
  `duration` int(11) NOT NULL,
  `end_of_submission` date NOT NULL,
  `start` date NOT NULL,
  `requirements` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `number_of_employees` int(10) UNSIGNED NOT NULL,
  `salary` decimal(8,2) UNSIGNED NOT NULL,
  `place` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `days` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `category_id` int(10) UNSIGNED NOT NULL,
  `title` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `job_opportunities`
--

INSERT INTO `job_opportunities` (`id`, `company_id`, `duration`, `end_of_submission`, `start`, `requirements`, `number_of_employees`, `salary`, `place`, `days`, `created_at`, `updated_at`, `category_id`, `title`) VALUES
(2, 2, 3, '2019-04-01', '2019-05-01', 'Some requirements Here\n- req1\n- req 2', 8, '80000.00', 'Damascus, Syria', 'Sunday, Monday, Wednesday', '2019-03-16 11:32:10', '2019-03-16 11:32:10', 4, 'Webdev'),
(3, 2, 3, '2019-04-01', '2019-05-01', 'Some requirements Here\n- req1\n- req 2', 8, '80000.00', 'Damascus, Syria', 'Sunday, Monday, Wednesday', '2019-03-16 11:41:35', '2019-03-16 11:41:35', 4, 'Webdev'),
(4, 2, 3, '2019-04-01', '2019-05-01', 'Some requirements Here\n- req1\n- req 2', 8, '80000.00', 'Damascus, Syria', 'Sunday, Monday, Wednesday', '2019-03-16 11:43:38', '2019-03-16 11:43:38', 4, 'Webdev');

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2019_03_09_122635_create_companies_table', 1),
(4, '2019_03_09_123114_create_branches_table', 1),
(5, '2019_03_09_123127_create_projects_table', 1),
(6, '2019_03_09_123218_create_job__opportunities_table', 1),
(7, '2019_03_09_123242_create_training_opportunities_table', 1),
(8, '2019_03_09_123326_create_company_photos_table', 1),
(9, '2019_03_16_110528_create_categories_table', 2),
(10, '2019_03_16_115038_add_cateory_id_forigrn_key', 3),
(11, '2019_03_16_132623_add_opportunitiy_title', 4);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `id` int(10) UNSIGNED NOT NULL,
  `company_id` int(10) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `company_id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(1, 2, 'Woodlet', 'E-Commernce Website', '2019-03-16 08:42:08', '2019-03-16 08:42:08');

-- --------------------------------------------------------

--
-- Table structure for table `training_opportunities`
--

CREATE TABLE `training_opportunities` (
  `id` int(10) UNSIGNED NOT NULL,
  `company_id` int(10) UNSIGNED NOT NULL,
  `duration` int(11) NOT NULL,
  `start` date NOT NULL,
  `requirements` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `paid` tinyint(1) NOT NULL,
  `number_of_trainees` int(10) UNSIGNED NOT NULL,
  `place` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `subject` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `category_id` int(10) UNSIGNED NOT NULL,
  `title` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `training_opportunities`
--

INSERT INTO `training_opportunities` (`id`, `company_id`, `duration`, `start`, `requirements`, `paid`, `number_of_trainees`, `place`, `subject`, `created_at`, `updated_at`, `category_id`, `title`) VALUES
(4, 2, 3, '2019-05-01', 'Some requirements Here\n- req1\n- req 2', 1, 8, 'Damascus, Syria', 'Web Development', '2019-03-16 11:31:36', '2019-03-16 11:31:36', 4, 'Web Development'),
(5, 2, 3, '2019-05-01', 'Some requirements Here\n- req1\n- req 2', 1, 8, 'Damascus, Syria', 'Web Development', '2019-03-16 11:41:39', '2019-03-16 11:41:39', 4, 'Web Development'),
(6, 2, 3, '2019-05-01', 'Some requirements Here\n- req1\n- req 2', 1, 8, 'Damascus, Syria', 'Web Development', '2019-03-16 11:43:44', '2019-03-16 11:43:44', 4, 'Web Development'),
(7, 2, 3, '2019-05-01', 'Some requirements Here\n- req1\n- req 2', 1, 8, 'Damascus, Syria', 'Web Development', '2019-03-16 11:49:11', '2019-03-16 11:49:11', 4, 'Web Development');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mobile` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` enum('1','2','3') COLLATE utf8mb4_unicode_ci NOT NULL,
  `company_id` int(10) UNSIGNED DEFAULT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `mobile`, `password`, `type`, `company_id`, `remember_token`, `created_at`, `updated_at`) VALUES
(13, 'inmood', 'mail@inmmod.com', '+963265874125', '$2y$10$F3tzf78NOJbl8aB6KdNh3OG/2DZHFvyB/u.nMf0bf1PJxvhM5kVx2', '2', NULL, NULL, '2019-03-15 11:25:05', '2019-03-15 11:25:05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branches`
--
ALTER TABLE `branches`
  ADD PRIMARY KEY (`id`),
  ADD KEY `branches_company_id_foreign` (`company_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `companies_user_id_foreign` (`user_id`);

--
-- Indexes for table `company_photos`
--
ALTER TABLE `company_photos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `company_photos_company_id_foreign` (`company_id`);

--
-- Indexes for table `job_opportunities`
--
ALTER TABLE `job_opportunities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `job_opportunities_company_id_foreign` (`company_id`),
  ADD KEY `job_opportunities_category_id_foreign` (`category_id`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `projects_company_id_foreign` (`company_id`);

--
-- Indexes for table `training_opportunities`
--
ALTER TABLE `training_opportunities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `training_opportunities_company_id_foreign` (`company_id`),
  ADD KEY `training_opportunities_category_id_foreign` (`category_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`),
  ADD UNIQUE KEY `users_mobile_unique` (`mobile`),
  ADD KEY `users_company_id_foreign` (`company_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `branches`
--
ALTER TABLE `branches`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `company_photos`
--
ALTER TABLE `company_photos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `job_opportunities`
--
ALTER TABLE `job_opportunities`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `training_opportunities`
--
ALTER TABLE `training_opportunities`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `branches`
--
ALTER TABLE `branches`
  ADD CONSTRAINT `branches_company_id_foreign` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `companies`
--
ALTER TABLE `companies`
  ADD CONSTRAINT `companies_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `company_photos`
--
ALTER TABLE `company_photos`
  ADD CONSTRAINT `company_photos_company_id_foreign` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `job_opportunities`
--
ALTER TABLE `job_opportunities`
  ADD CONSTRAINT `job_opportunities_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `job_opportunities_company_id_foreign` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `projects_company_id_foreign` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `training_opportunities`
--
ALTER TABLE `training_opportunities`
  ADD CONSTRAINT `training_opportunities_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `training_opportunities_company_id_foreign` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_company_id_foreign` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
